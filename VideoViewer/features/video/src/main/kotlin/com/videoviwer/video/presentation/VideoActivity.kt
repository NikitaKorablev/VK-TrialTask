package com.videoviwer.video.presentation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
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
        val video = Gson().fromJson(videoJson, BaseVideo::class.java)
        binding.videoTitle?.text = video.name

        if (savedInstanceState != null) {
            // Восстановление состояния воспроизведения
            playWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY)
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW)
            playbackPosition = savedInstanceState.getLong(PLAYBACK_POSITION)
        }

        player = ExoPlayer.Builder(this).build()
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                val text = "Ошибка воспроизведения: " + error.message
                Toast.makeText(this@VideoActivity, text, Toast.LENGTH_LONG).show()
                Log.e("ExoPlayer", text)
            }
        })
        binding.playerView.player = player

        video?.let {
            val mediaItem = MediaItem.fromUri(Uri.parse(it.url))
            player.setMediaItem(mediaItem)

            player.prepare()
            player.playWhenReady = playWhenReady
            player.seekTo(currentWindow, playbackPosition)
        } ?: run {
            val toastMessage = "Sorry, but the video was not found."
            Toast.makeText(this, toastMessage,Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(PLAY_WHEN_READY, playWhenReady)
        outState.putInt(CURRENT_WINDOW, currentWindow)
        outState.putLong(PLAYBACK_POSITION, playbackPosition)
    }

    override fun onStop() {
        super.onStop()
        playWhenReady = player.playWhenReady
        playbackPosition = player.currentPosition
        currentWindow = player.currentWindowIndex
        player.release()
    }

    companion object {
        const val PLAY_WHEN_READY = "play_when_ready"
        const val CURRENT_WINDOW = "current_window"
        const val PLAYBACK_POSITION = "playback_position"
    }
}