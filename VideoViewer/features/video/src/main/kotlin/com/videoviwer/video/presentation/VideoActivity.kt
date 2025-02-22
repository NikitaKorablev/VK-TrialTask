package com.videoviwer.video.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.gson.Gson
import com.videoviwer.core.data.BaseVideo
import com.videoviwer.video.R
import com.videoviwer.video.databinding.ActivityVideoBinding


class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private lateinit var player: ExoPlayer
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, systemBars.bottom)
            insets
        }

        val videoJson = intent.getStringExtra("VIDEO_DATA")
        Log.d("VideoActivity", videoJson.toString())
        val video = Gson().fromJson(videoJson, BaseVideo::class.java)
        binding.videoTitle.text = video.name

        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player
        Log.d("VideoActivity", video.toString())
        video?.let {
            val mediaItem = MediaItem.fromUri(Uri.parse(it.url))
            player.setMediaItem(mediaItem)

            // Подготовка плеера
            player.prepare()
            player.playWhenReady = playWhenReady
            player.seekTo(currentWindow, playbackPosition)
        }
    }

    override fun onStop() {
        super.onStop()
        playWhenReady = player.playWhenReady
        playbackPosition = player.currentPosition
        currentWindow = player.currentWindowIndex
        player.release()
    }
}