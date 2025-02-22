package com.videoviwer.data

import com.videoviwer.core.data.Video
import com.videoviwer.core.domain.VideoService
import com.videoviwer.domain.repository.TopPopularVideosRepositoryInterface

class TopPopularVideosRepositoryImpl(
    private var videoService: VideoService
): TopPopularVideosRepositoryInterface {
    override suspend fun getTopPopularVideos(): List<Video> {
        return videoService.getTopPopularityVideo()
    }
}