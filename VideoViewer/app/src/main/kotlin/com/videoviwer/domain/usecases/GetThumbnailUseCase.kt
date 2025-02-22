package com.videoviwer.domain.usecases

import android.graphics.Bitmap
import com.videoviwer.domain.repository.ThumbnailRepositoryInterface

class GetThumbnailUseCase(private val repository: ThumbnailRepositoryInterface) {
    suspend fun execute(url: String): Bitmap? {
        return repository.getThumbnail(url)
    }
}