package com.videoviwer.di

import android.content.Context
import com.videoviwer.core.di.NetworkModule
import com.videoviwer.core.domain.VideoService
import com.videoviwer.data.ThumbnailRepositoryImpl
import com.videoviwer.data.TopPopularVideosRepositoryImpl
import com.videoviwer.data.TopPopularVideosStorage
import com.videoviwer.data.TopPopularVideosStorageSharedPrefs
import com.videoviwer.domain.repository.ThumbnailRepositoryInterface
import com.videoviwer.domain.repository.TopPopularVideosRepositoryInterface
import com.videoviwer.domain.usecases.GetThumbnailUseCase
import com.videoviwer.domain.usecases.GetTopPopularVideosUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
        videoService: VideoService,
        videosStorage: TopPopularVideosStorage
    ): TopPopularVideosRepositoryInterface {
        return TopPopularVideosRepositoryImpl(videoService, videosStorage)
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
}