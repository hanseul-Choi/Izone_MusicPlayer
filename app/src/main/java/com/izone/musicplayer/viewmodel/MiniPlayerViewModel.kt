package com.izone.musicplayer.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izone.musicplayer.repository.storage.StorageListener
import com.izone.musicplayer.repository.storage.StorageRepository
import java.lang.Exception

class MiniPlayerViewModel(private val storageRepository: StorageRepository) : ViewModel() {

    // prefetch를 하면 누를때마다 firebase storage에 접근할 필요가 없긴함
    // 따라서 List로 받는게 좋아보임! -> 시간이 많이 걸리지 않을까?
    // Map형식으로 받고 데이터가 있을 때는 firebase에 접근 안하는게 효율적이지 않을까?

    private val _musicUri = MutableLiveData<Uri>()
    val musicUri: LiveData<Uri> = _musicUri

    // 문제점? musicItem이 null로 먼저 받아지고 그 후에 value를 받아 문제
    fun getMusicItem(musicUri: String) {
        storageRepository.getMusicItem(musicUri, object : StorageListener {
            override fun onSuccess(uri: Uri) {
                Log.d("test", "music uri is $uri")
            }

            override fun onFailed(e: Exception) {
                Log.e("error", e.message ?: "error is occurred")
            }
        })
    }
}