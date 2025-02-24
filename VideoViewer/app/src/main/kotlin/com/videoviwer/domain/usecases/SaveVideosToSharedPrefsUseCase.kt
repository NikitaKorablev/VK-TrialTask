package com.videoviwer.domain.usecases

import com.videoviwer.core.data.VideosData
import com.videoviwer.domain.repository.TopPopularVideosSharedPrefsRepositoryInterface

class SaveVideosToSharedPrefsUseCase(
    private val repository: TopPopularVideosSharedPrefsRepositoryInterface
) {
    fun execute(videosData: VideosData.CorrectData) {
        repository.saveVideosToSharedPrefs(videosData)
    }
}