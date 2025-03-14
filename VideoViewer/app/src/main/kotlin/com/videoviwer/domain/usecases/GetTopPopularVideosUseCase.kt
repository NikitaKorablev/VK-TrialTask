package com.videoviwer.domain.usecases

import com.videoviwer.core.data.VideosData
import com.videoviwer.domain.repository.TopPopularVideosRepositoryInterface

class GetTopPopularVideosUseCase(
    private val repository: TopPopularVideosRepositoryInterface
) {
    suspend fun execute(): VideosData {
        return repository.getTopPopularVideos()
    }
}