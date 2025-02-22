package com.videoviwer.domain.repository

import android.graphics.Bitmap

interface ThumbnailRepositoryInterface {
    suspend fun getThumbnail(url: String): Bitmap?
}