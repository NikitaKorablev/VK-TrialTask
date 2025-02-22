package com.videoviwer.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.videoviwer.core.data.Video
import com.videoviwer.domain.usecases.GetThumbnailUseCase
import com.videoviwer.domain.usecases.GetTopPopularVideosUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
            val list = getTopPopularVideosUseCase.execute()
            Log.d("ViewModel", list.size.toString())
            for (video in list)
                updateVideoThumbnail(video)
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