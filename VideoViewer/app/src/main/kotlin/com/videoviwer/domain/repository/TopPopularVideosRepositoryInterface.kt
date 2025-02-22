package com.videoviwer.domain.repository

import com.videoviwer.core.data.BaseVideo
import com.videoviwer.data.Video

interface TopPopularVideosRepositoryInterface {
    suspend fun getTopPopularVideos(): List<Video>
}