package com.izone.musicplayer.repository.storage

import android.net.Uri
import java.lang.Exception

interface StorageListener {
    fun onSuccess(uri: Uri)
    fun onFailed(e: Exception)
}