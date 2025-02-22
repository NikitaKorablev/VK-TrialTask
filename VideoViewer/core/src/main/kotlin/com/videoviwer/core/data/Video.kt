package com.videoviwer.core.data

import android.graphics.Bitmap

data class Video(
    val name: String,
    val duration: String,
    val url: String,
    var thumbnail: Bitmap?
)
