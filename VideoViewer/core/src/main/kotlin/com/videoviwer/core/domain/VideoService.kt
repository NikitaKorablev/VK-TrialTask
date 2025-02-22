package com.videoviwer.core.domain

import com.videoviwer.core.data.BaseVideo
import retrofit2.http.GET

interface VideoService {
    @GET("top_videos")
    suspend fun getTopPopularityVideo(): List<BaseVideo>
}