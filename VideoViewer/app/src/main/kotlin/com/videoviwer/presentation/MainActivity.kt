package com.videoviwer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.videoviwer.R
import com.videoviwer.core.data.BaseVideo
import com.videoviwer.databinding.ActivityMainBinding
import com.videoviwer.di.VideoWatcherDepsProvider
import com.videoviwer.domain.repository.OnItemClickListener
import com.videoviwer.presentation.viewmodel.VideosListViewModel
import com.videoviwer.presentation.viewmodel.VideosListViewModelFactory
import com.google.gson.Gson
import com.videoviwer.core.data.Video
import com.videoviwer.video.presentation.VideoActivity

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VideoRecyclerAdapter

    private val viewModel: VideosListViewModel by viewModels {
        VideosListViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initDI()
        binding.swipeRefreshLayout

        viewModel.videosList.observe(this) { videosList ->
            adapter = VideoRecyclerAdapter(videosList.toMutableList(), this)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }

        viewModel.getTopVideos()
    }

    private fun initDI() {
        val videoWatcherComponent =
            (applicationContext as VideoWatcherDepsProvider).getVideoWatcherComponent()
        videoWatcherComponent.inject(viewModel)
    }

    override fun onItemClick(video: Video) {
        val intent = Intent(baseContext, VideoActivity::class.java)
        val baseVideo = castVideoToBaseVideo(video)
        intent.putExtra("VIDEO_DATA", Gson().toJson(baseVideo))
        startActivity(intent)
    }

    private fun castVideoToBaseVideo(video: Video) : BaseVideo {
        return BaseVideo(video.name, video.url)
    }
}