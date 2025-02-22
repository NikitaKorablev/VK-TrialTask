package com.videoviwer.domain.repository

import com.videoviwer.core.data.Video

interface TopPopularVideosRepositoryInterface {
    suspend fun getTopPopularVideos(): List<Video>
}