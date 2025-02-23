package com.videoviwer.core.data

sealed class VideosData {
    data class CorrectData(val videosList: List<Video>): VideosData()
    data class InvalidData(val message: String): VideosData()
}