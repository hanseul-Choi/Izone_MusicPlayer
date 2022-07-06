package com.izone.musicplayer.repository.storage

interface StorageDataSource {
    fun getMusicItem(musicUri: String, listener: StorageListener)
}