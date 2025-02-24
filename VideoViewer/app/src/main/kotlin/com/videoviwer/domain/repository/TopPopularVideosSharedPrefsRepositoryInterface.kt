package com.videoviwer.domain.repository

import com.videoviwer.core.data.VideosData

interface TopPopularVideosSharedPrefsRepositoryInterface {
    fun getVideosFromSharedPrefs(): VideosData
    fun saveVideosToSharedPrefs(videosData: VideosData.CorrectData)
}