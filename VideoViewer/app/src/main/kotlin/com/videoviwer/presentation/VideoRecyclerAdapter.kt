package com.videoviwer.presentation

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.videoviwer.core.data.Video
import com.videoviwer.databinding.VideoItemLayoutBinding
import com.videoviwer.domain.repository.OnItemClickListener

class VideoRecyclerAdapter(
    private val videosList: List<Video>,
    private val listener: OnItemClickListener? = null
): RecyclerView.Adapter<VideoRecyclerAdapter.VideoViewHolder> (){

    class VideoViewHolder(val binding: VideoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding =
            VideoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return VideoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videosList[position]
        holder.binding.videoName.text = video.name
        holder.binding.videoDuration.text = video.duration

        holder.binding.videoName.maxLines = 2
        holder.binding.videoName.ellipsize = TextUtils.TruncateAt.END

        Log.d("AdapterStatus", video.toString())
        video.thumbnail?.let {
            Log.d("AdapterStatus", "New thumbnail")
            holder.binding.thumbnail.setImageBitmap(it)
        }

        holder.itemView.setOnClickListener {
            Log.d("RecyclerItem", "Clicked")
            listener?.onItemClick(video)
        }
    }
}