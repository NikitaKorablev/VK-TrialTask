package com.videoviwer.data

import com.videoviwer.core.data.VideosData
import com.videoviwer.core.domain.VideoService
import com.videoviwer.domain.repository.TopPopularVideosRepositoryInterface

class TopPopularVideosRepositoryImpl(
    private val videoService: VideoService,
): TopPopularVideosRepositoryInterface {
    override suspend fun getTopPopularVideos(): VideosData {
        return try {
            val list = videoService.getTopPopularityVideos()
            VideosData.CorrectData(list)
        } catch (e: Exception) {
            VideosData.InvalidData("Ошибка получения данных. ${e.message}")
        }
    }
}