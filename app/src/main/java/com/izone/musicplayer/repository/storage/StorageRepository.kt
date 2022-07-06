package com.izone.musicplayer.repository.storage

class StorageRepository(private val storageRemoteDataSource: StorageRemoteDataSource) {
    fun getMusicItem(musicUri: String, listener: StorageListener) {
        storageRemoteDataSource.getMusicItem(musicUri, listener)
    }
}