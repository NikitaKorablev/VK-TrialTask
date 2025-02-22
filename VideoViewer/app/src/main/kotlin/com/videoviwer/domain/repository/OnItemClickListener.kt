package com.videoviwer.domain.repository

import com.videoviwer.core.data.Video

interface OnItemClickListener {
    fun onItemClick(video: Video)
}