package com.videoviwer.domain.repository

import com.videoviwer.core.data.VideosData

interface TopPopularVideosRepositoryInterface {
    suspend fun getTopPopularVideos(isRefresh: Boolean = false): VideosData
}