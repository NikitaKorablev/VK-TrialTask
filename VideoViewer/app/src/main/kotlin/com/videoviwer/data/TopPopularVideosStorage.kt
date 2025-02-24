package com.videoviwer.data

import com.videoviwer.core.data.VideosData

interface TopPopularVideosStorage {
    fun getTopPopularVideos(): VideosData
    fun saveTopPopularVideos(videos: VideosData.CorrectData)
}