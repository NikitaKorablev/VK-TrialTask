package com.videoviwer.domain.repository

import com.videoviwer.core.data.BaseVideo
import com.videoviwer.data.Video

interface OnItemClickListener {
    fun onItemClick(video: Video)
}