package com.videoviwer.utils

import androidx.recyclerview.widget.DiffUtil
import com.videoviwer.core.data.Video

class VideoDiffCallback(
    private val oldList: List<Video>,
    private val newList: List<Video>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url == newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}