package com.videoviwer.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.videoviwer.core.data.VideosData
import javax.inject.Inject

class TopPopularVideosStorageSharedPrefs @Inject constructor(context: Context)
    : TopPopularVideosStorage {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun getTopPopularVideos(): VideosData {
        val json = sharedPreferences.getString(DATA_TAG, null)

        return try {
            gson.fromJson(json, VideosData.CorrectData::class.java)
        } catch (e: Exception) {
            return VideosData.InvalidData("Ошибка получения данных. ${e.message}")
        }
    }

    override fun saveTopPopularVideos(videos: VideosData.CorrectData) {
        val json = gson.toJson(videos)
        sharedPreferences.edit().putString(DATA_TAG, json).apply()
    }

    companion object {
        const val SHARED_PREFS_NAME = "VIDEO_PREFS"
        const val DATA_TAG = "VIDEOS_LIST"
    }
}