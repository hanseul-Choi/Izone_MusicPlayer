package com.izone.musicplayer.repository.storage

interface StorageDataSource {
    fun getMusicItem(musicUri: String, listener: StorageListener)
    fun getAlbumImageItem(albumUri: String, listener: StorageListener)
}