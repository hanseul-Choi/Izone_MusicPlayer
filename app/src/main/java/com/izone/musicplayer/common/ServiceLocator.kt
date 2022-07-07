package com.izone.musicplayer.common

import com.izone.musicplayer.network.ApiClient
import com.izone.musicplayer.network.StorageApiClient

object ServiceLocator {
    private var apiClient: ApiClient? = null

    fun provideMusicApi(): ApiClient {
        return apiClient ?: kotlin.run {
            ApiClient.create().also {
                apiClient = it
            }
        }
    }

    private var storageApiClient: StorageApiClient? = null

    fun provideStorageApi(): StorageApiClient {
        return storageApiClient ?: kotlin.run {
            StorageApiClient().also {
                storageApiClient = it
            }
        }
    }
}