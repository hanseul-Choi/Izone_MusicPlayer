package com.izone.musicplayer.repository.storage

import javax.inject.Inject

class StorageRepository @Inject constructor(private val storageRemoteDataSource: StorageDataSource) {
    fun getMusicItem(musicUri: String, listener: StorageListener) {
        storageRemoteDataSource.getMusicItem(musicUri, listener)
    }
}