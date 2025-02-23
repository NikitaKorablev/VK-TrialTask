package com.videoviwer.data

import com.videoviwer.core.data.VideosData
import com.videoviwer.core.domain.VideoService

interface TopPopularVideosStorage {
    suspend fun getTopPopularVideos(videoService: VideoService): VideosData
}