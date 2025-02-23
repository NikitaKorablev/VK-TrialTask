package com.videoviwer.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.videoviwer.core.data.Video
import com.videoviwer.core.data.VideosData
import com.videoviwer.domain.usecases.GetThumbnailUseCase
import com.videoviwer.domain.usecases.GetTopPopularVideosUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideosListViewModel: ViewModel() {
    @Inject
    lateinit var getThumbnailUseCase: GetThumbnailUseCase

    @Inject
    lateinit var getTopPopularVideosUseCase: GetTopPopularVideosUseCase

    private val _videosList = MutableLiveData<List<Video>>()
    val videosList: LiveData<List<Video>> get() = _videosList

    fun refreshVideosList() {
        _videosList.value = emptyList()
        getTopVideos()
    }

    fun getTopVideos() {
        viewModelScope.launch {
            when (val videosData = getTopPopularVideosUseCase.execute()) {
                is VideosData.CorrectData -> {
                    for (video in videosData.videosList)
                        updateVideoThumbnail(video)
                }

                is VideosData.InvalidData -> {

                }
            }

        }
    }

    private fun updateVideoThumbnail(video: Video) {
        viewModelScope.launch {
            val thumbnail = getThumbnailUseCase.execute(video.url)
            thumbnail?.let {
                video.thumbnail = it
                addVideosList(video)
                Log.d("GetThumbnail", "Thumbnail found: ${video.name}")
            } ?: run {
                Log.e("GetThumbnail", "Thumbnail undefined: ${video.name}")
            }
        }
    }

    private fun addVideosList(video: Video) {
        val curList = _videosList.value?.toMutableList() ?: mutableListOf()
        curList.add(video)
        _videosList.value = curList
    }
}