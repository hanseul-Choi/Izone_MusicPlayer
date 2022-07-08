package com.izone.musicplayer.repository.storage

import android.net.Uri

interface StorageListener {
    fun onSuccess(uri: Uri)
    fun onFailed(e: Exception)
}