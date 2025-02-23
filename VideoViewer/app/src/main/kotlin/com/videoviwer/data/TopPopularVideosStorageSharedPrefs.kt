package com.videoviwer.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.videoviwer.core.data.VideosData
import com.videoviwer.core.domain.VideoService
import javax.inject.Inject

class TopPopularVideosStorageSharedPrefs @Inject constructor(context: Context)
    : TopPopularVideosStorage {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    override suspend fun getTopPopularVideos(videoService: VideoService): VideosData {
        val json = sharedPreferences.getString(DATA_TAG, null)

        return try {
            gson.fromJson(json, VideosData.CorrectData::class.java)
        } catch (e: Exception) {
            val list = videoService.getTopPopularityVideos()
            if (list.isEmpty())
                return VideosData.InvalidData("Ошибка получения данных. ${e.message}")
            else {
                val videosData = VideosData.CorrectData(list)
                saveTopPopularVideos(videosData)
                return videosData
            }
        }
    }

    private fun saveTopPopularVideos(videos: VideosData.CorrectData) {
        val json = gson.toJson(videos)
        sharedPreferences.edit().putString(DATA_TAG, json).apply()
    }

    companion object {
        const val SHARED_PREFS_NAME = "VIDEO_PREFS"
        const val DATA_TAG = "VIDEOS_LIST"
    }
}