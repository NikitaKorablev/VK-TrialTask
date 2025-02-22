package com.videoviwer.application

import android.app.Application
import com.videoviwer.di.AppComponent
import com.videoviwer.di.DaggerAppComponent
import com.videoviwer.di.DaggerVideoWatcherComponent
import com.videoviwer.di.VideoWatcherComponent
import com.videoviwer.di.VideoWatcherDepsProvider

class VideoWatcherApplication: Application(), VideoWatcherDepsProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getVideoWatcherComponent(): VideoWatcherComponent {
        return DaggerVideoWatcherComponent.builder().context(this).build()
    }
}