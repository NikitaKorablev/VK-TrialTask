package com.videoviwer.domain.usecases

import com.videoviwer.core.data.VideosData
import com.videoviwer.domain.repository.TopPopularVideosSharedPrefsRepositoryInterface

class GetVideosFromSharedPrefsUseCase(
    private val repository: TopPopularVideosSharedPrefsRepositoryInterface
) {
    fun execute(): VideosData {
        return repository.getVideosFromSharedPrefs()
    }
}