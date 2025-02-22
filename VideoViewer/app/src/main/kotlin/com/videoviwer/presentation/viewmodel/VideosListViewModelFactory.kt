package com.videoviwer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VideosListViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideosListViewModel::class.java)) {
            return VideosListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}