package com.videoviwer.di

import android.content.Context
import com.videoviwer.core.di.NetworkModule
import com.videoviwer.core.domain.VideoService
import com.videoviwer.data.ThumbnailRepositoryImpl
import com.videoviwer.data.TopPopularVideosRepositoryImpl
import com.videoviwer.data.TopPopularVideosStorage
import com.videoviwer.data.TopPopularVideosStorageSharedPrefs
import com.videoviwer.data.VideosFromSharedPrefsRepositoryImpl
import com.videoviwer.domain.repository.ThumbnailRepositoryInterface
import com.videoviwer.domain.repository.TopPopularVideosRepositoryInterface
import com.videoviwer.domain.repository.TopPopularVideosSharedPrefsRepositoryInterface
import com.videoviwer.domain.usecases.GetThumbnailUseCase
import com.videoviwer.domain.usecases.GetTopPopularVideosUseCase
import com.videoviwer.domain.usecases.GetVideosFromSharedPrefsUseCase
import com.videoviwer.domain.usecases.SaveVideosToSharedPrefsUseCase
import dagger.Module
import dagger.Provides

@Module(
    includes = [ThumbnailModule::class, TopVideosModule::class, NetworkModule::class]
)
class VideosListModule

@Module
class ThumbnailModule {
    @Provides
    fun provideThumbnailRepository(): ThumbnailRepositoryInterface {
        return ThumbnailRepositoryImpl()
    }

    @Provides
    fun provideGetThumbnailUseCase(
        repository: ThumbnailRepositoryInterface
    ): GetThumbnailUseCase {
        return GetThumbnailUseCase(repository)
    }
}

@Module
class TopVideosModule {
    @Provides
    fun provideTopPopularVideosRepository(
        videoService: VideoService
    ): TopPopularVideosRepositoryInterface {
        return TopPopularVideosRepositoryImpl(videoService)
    }

    @Provides
    fun providesVideosFromSharedPrefsRepository(
        videosStorage: TopPopularVideosStorage
    ): TopPopularVideosSharedPrefsRepositoryInterface {
        return VideosFromSharedPrefsRepositoryImpl(videosStorage)
    }

    @Provides
    fun provideTopPopularVideosStorage(context: Context)
        : TopPopularVideosStorage {
        return TopPopularVideosStorageSharedPrefs(context)
    }

    @Provides
    fun provideGetTopPopularVideosUseCase(
        repository: TopPopularVideosRepositoryInterface
    ): GetTopPopularVideosUseCase {
        return GetTopPopularVideosUseCase(repository)
    }

    @Provides
    fun provideGetVideosFromSharedPrefsUseCase(
        repository: TopPopularVideosSharedPrefsRepositoryInterface
    ): GetVideosFromSharedPrefsUseCase {
        return GetVideosFromSharedPrefsUseCase(repository)
    }

    @Provides
    fun provideSaveVideosFromSharedPrefsUseCase(
        repository: TopPopularVideosSharedPrefsRepositoryInterface
    ): SaveVideosToSharedPrefsUseCase {
        return SaveVideosToSharedPrefsUseCase(repository)
    }
}