package com.videoviwer.di

import android.content.Context
import com.videoviwer.presentation.viewmodel.VideosListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [VideosListModule::class])
interface VideoWatcherComponent {

    fun inject(viewModel: VideosListViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): VideoWatcherComponent
    }
}

interface VideoWatcherDepsProvider {
    fun getVideoWatcherComponent(): VideoWatcherComponent
}