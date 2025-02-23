package com.videoviwer.data

import com.videoviwer.core.data.VideosData
import com.videoviwer.core.domain.VideoService
import com.videoviwer.domain.repository.TopPopularVideosRepositoryInterface

class TopPopularVideosRepositoryImpl(
    private var videoService: VideoService,
    private var videosStorage: TopPopularVideosStorage
): TopPopularVideosRepositoryInterface {
    override suspend fun getTopPopularVideos(): VideosData {
        return videosStorage.getTopPopularVideos(videoService)
    }
}