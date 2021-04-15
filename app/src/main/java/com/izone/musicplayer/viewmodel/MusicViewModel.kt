package com.izone.musicplayer.viewmodel

import android.app.DownloadManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.model.MusicModel
import com.izone.musicplayer.repository.MusicRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private val _musicRepositories = MutableLiveData<List<MusicItems>>()
    val musicRepositories = _musicRepositories

    fun requestMusicRepositories(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            musicRepository.getRepositories(query)?.let{ response ->
                if(response.isSuccessful) {
                    response.body()?.let {
                        _musicRepositories.postValue(it.musicList)
                    }
                }
            }
        }
    }
}