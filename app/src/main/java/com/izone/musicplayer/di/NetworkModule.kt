package com.izone.musicplayer.di

import com.izone.musicplayer.network.ApiClient
import com.izone.musicplayer.network.StorageApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiClient() : ApiClient {
        return ApiClient.create()
    }

    @Singleton
    @Provides
    fun provideStorageApiClient() : StorageApiClient {
        return StorageApiClient.getInstance()
    }
}