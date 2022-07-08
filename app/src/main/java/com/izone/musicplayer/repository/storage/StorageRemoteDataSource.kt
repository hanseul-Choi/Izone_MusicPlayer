package com.izone.musicplayer.repository.storage

import com.izone.musicplayer.network.StorageApiClient

class StorageRemoteDataSource(private val storageApiClient: StorageApiClient) : StorageDataSource {
    override fun getMusicItem(musicUri: String, listener: StorageListener) {
        storageApiClient.getMusicUri(musicUri, listener)
    }
}