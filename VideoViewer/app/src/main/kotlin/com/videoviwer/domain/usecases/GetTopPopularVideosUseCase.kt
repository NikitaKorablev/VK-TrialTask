package com.videoviwer.domain.usecases

import com.videoviwer.data.Video
import com.videoviwer.domain.repository.TopPopularVideosRepositoryInterface

class GetTopPopularVideosUseCase(
    private val repository: TopPopularVideosRepositoryInterface
) {
    suspend fun execute(): List<Video> {
        return repository.getTopPopularVideos()
    }
}