package com.videoviwer.data

import android.util.Log
import com.videoviwer.core.data.Video
import com.videoviwer.core.domain.VideoService
import com.videoviwer.domain.repository.TopPopularVideosRepositoryInterface

class TopPopularVideosRepositoryImpl(
    private var videoService: VideoService
): TopPopularVideosRepositoryInterface {
    override suspend fun getTopPopularVideos(): List<Video> {
        val list = videoService.getTopPopularityVideo()
        Log.d("Repo", list.size.toString())
        return list
    }
}