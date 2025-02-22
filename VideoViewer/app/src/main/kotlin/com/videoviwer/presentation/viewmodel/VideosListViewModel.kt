package com.videoviwer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.videoviwer.core.data.BaseVideo
import com.videoviwer.data.Video
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

    fun getTopVideos() {
        viewModelScope.launch {
            val list = getTopPopularVideosUseCase.execute()
            for (video in list)
                updateVideoThumbnail(video)
        }
    }

    private fun updateVideoThumbnail(video: Video) {
        addVideosList(video)

        viewModelScope.launch {
            val thumbnail = getThumbnailUseCase.execute(video.url)
            thumbnail?.let {
                video.thumbnail = it
                updateVideosList()
            }
        }
    }

    private fun addVideosList(video: Video) {
        val curList = _videosList.value?.toMutableList() ?: mutableListOf()
        curList.add(video)
        _videosList.value = curList
    }

    private fun updateVideosList() {
        _videosList.value = _videosList.value?.toMutableList() ?: mutableListOf()
    }

    private fun refreshListener() {

    }
}