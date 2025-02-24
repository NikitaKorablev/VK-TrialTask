package com.videoviwer.data

import com.videoviwer.core.data.VideosData
import com.videoviwer.domain.repository.TopPopularVideosSharedPrefsRepositoryInterface

class VideosFromSharedPrefsRepositoryImpl(
    private val videosStorage: TopPopularVideosStorage
): TopPopularVideosSharedPrefsRepositoryInterface {
    override fun getVideosFromSharedPrefs(): VideosData {
        return videosStorage.getTopPopularVideos()
    }

    override fun saveVideosToSharedPrefs(videosData: VideosData.CorrectData) {
        videosStorage.saveTopPopularVideos(videosData)
    }
}