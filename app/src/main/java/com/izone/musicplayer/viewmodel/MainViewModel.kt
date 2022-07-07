package com.izone.musicplayer.viewmodel

import android.util.Log
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izone.musicplayer.Event
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.repository.music.MusicRepository
import com.izone.musicplayer.repository.storage.StorageListener
import com.izone.musicplayer.repository.storage.StorageRepository
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(
    private val musicRepository: MusicRepository,
    private val storageRepository: StorageRepository)
    : ViewModel() {

    private val _musicList = MutableLiveData<List<MusicItems>>()
    val musicList: LiveData<List<MusicItems>> = _musicList

    private val job = CoroutineScope(Dispatchers.Default)

    fun requestIzoneRepositories() {
        viewModelScope.launch {
            val musicItems = musicRepository.getIzoneRepository()
            _musicList.value = musicItems
        }
    }

    fun requestBtsRepositories() {
        viewModelScope.launch {
            val musicItems = musicRepository.getBtsRepository()
            _musicList.value = musicItems
        }
    }

    fun requestOhmygirlRepositories() {
        viewModelScope.launch {
            val musicItems = musicRepository.getOhmygirlRepository()
            _musicList.value = musicItems
        }
    }

    private val mediaPlayer by lazy {
        MediaPlayer()
    }

    // player pos
    private val _musicPosition = MutableLiveData<Int>()
    val musicPosition = _musicPosition

    // music value
    private val _musicEvent = MutableLiveData<Event<String>>()
    val musicEvent: LiveData<Event<String>> = _musicEvent

    private val _showPlayer = MutableLiveData<Boolean>()
    val showPlayer: LiveData<Boolean> = _showPlayer

    // music item 클릭시 동작할 메소드
    fun clickMusicItem(music: String) {
        _showPlayer.value = true

        storageRepository.getMusicItem(music, object : StorageListener {
            override fun onSuccess(uri: Uri) {
                _musicEvent.value = Event(uri.toString())
            }

            override fun onFailed(e: Exception) {

            }
        })
    }

    fun setMusic(uri: String) {
        mediaPlayer.apply {
            reset()
            setDataSource(uri)
            prepare()
            start()
        }
    }

    fun playMusic() {
        mediaPlayer.apply {
            start()
        }

        job.launch {
            musicStateCheck()
        }
    }

    fun stopMusic() {
        mediaPlayer.apply {
            pause()
        }

        if(job.isActive) job.cancel()
    }

    fun setPosition(pos: Int) {
        if (pos >= musicList.value!!.size) {
            _musicPosition.postValue(0)
        } else {
            if(_musicPosition.value != pos) {
                _musicPosition.postValue(pos)
            }
        }
    }

    private fun musicStateCheck() {
        while(mediaPlayer.isPlaying) {
            val curpos = mediaPlayer.currentPosition
            val duration = mediaPlayer.duration

            Log.d("test", "duration is $duration , $curpos")

            if(curpos >= duration - 20 && curpos != 0 && duration != 0) {
                setPosition(musicPosition.value!!.plus(1))
                break
            }
        }
    }
}