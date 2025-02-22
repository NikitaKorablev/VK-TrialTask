package com.videoviwer.core.di

import com.videoviwer.core.domain.VideoService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideWeatherService(@Named("baseUrl") baseUrl: String): VideoService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideoService::class.java)
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = "http://147.45.246.48:5532/"
}