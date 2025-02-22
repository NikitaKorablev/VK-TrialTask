package com.videoviwer.data

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.util.Log
import com.videoviwer.domain.repository.ThumbnailRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ThumbnailRepositoryImpl: ThumbnailRepositoryInterface {
    override suspend fun getThumbnail(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val mmr = MediaMetadataRetriever()
                mmr.setDataSource(url, HashMap()) // Используйте URL в виде строки
                val bitmap = mmr.getFrameAtTime(1000000) // Извлечение кадра на 1-й секунде
                mmr.release()
                bitmap
            } catch (e: Exception) {
                Log.e("GetVideoThumbnail", e.message.toString())
                e.printStackTrace()
                null
            }
        }

    }
}