package com.izone.musicplayer.di

import com.izone.musicplayer.common.MPConst
import com.izone.musicplayer.network.ApiClient
import com.izone.musicplayer.network.StorageApiClient
import com.izone.musicplayer.repository.music.MusicDataSource
import com.izone.musicplayer.repository.music.MusicRemoteDataSource
import com.izone.musicplayer.repository.storage.StorageDataSource
import com.izone.musicplayer.repository.storage.StorageRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStorageDataSource() : StorageDataSource {
        return StorageRemoteDataSource(StorageApiClient.getInstance())
    }


    @Provides
    fun provideMusicDataSource(apiClient: ApiClient) : MusicDataSource {
        return MusicRemoteDataSource(apiClient)
    }
}