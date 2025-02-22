package com.videoviwer.core.domain

import com.videoviwer.core.data.Video
import retrofit2.http.GET

interface VideoService {
    @GET("top_videos")
    suspend fun getTopPopularityVideo(): List<Video>
}