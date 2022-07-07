package com.izone.musicplayer.viewmodel

import android.util.Log
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izone.musicplayer.common.Event
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.repository.music.MusicRepository
import com.izone.musicplayer.repository.storage.StorageListener
import com.izone.musicplayer.repository.storage.StorageRepository
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(
    private val musicRepository: MusicRepository,
    private val storageRepository: StorageRepository
) : ViewModel() {

    // music list data
    private val _musicList = MutableLiveData<List<MusicItems>>()
    val musicList: LiveData<List<MusicItems>> = _musicList

    // music player data
    private val job = CoroutineScope(Dispatchers.Default)

    private val mediaPlayer by lazy {
        MediaPlayer()
    }

    private val _musicPosition = MutableLiveData<Int>()
    val musicPosition: LiveData<Int> = _musicPosition

    // click event data
    private val _musicEvent = MutableLiveData<Event<String>>()
    val musicEvent: LiveData<Event<String>> = _musicEvent

    private val _showPlayer = MutableLiveData<Boolean>()
    val showPlayer: LiveData<Boolean> = _showPlayer

    private val _isMusicPlay = MutableLiveData<Boolean>()
    val isMusicPlay: LiveData<Boolean> = _isMusicPlay

    init {
        _isMusicPlay.value = false
    }

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

    // music item 클릭시 동작할 메소드
    fun clickMusicItem(music: String, pos: Int) {
        _showPlayer.value = true
        _musicPosition.value = pos

        storageRepository.getMusicItem(music, object : StorageListener {
            override fun onSuccess(uri: Uri) {
                _musicEvent.value = Event(uri.toString())
            }

            override fun onFailed(e: Exception) {

            }
        })
    }

    fun setMusic(uri: String) {
        _isMusicPlay.value = true
        mediaPlayer.apply {
            reset()
            setDataSource(uri)
            prepare()
            start()
        }
    }

    fun playMusic() {
        _isMusicPlay.value = true
        Log.d("music","play music")
        mediaPlayer.apply {
            start()
        }

        job.launch {
            musicStateCheck()
        }
    }

    fun stopMusic() {
        _isMusicPlay.value = false

        Log.d("music","stop music")
        mediaPlayer.apply {
            pause()
        }

        if (job.isActive) job.cancel()
    }

    fun setPosition(pos: Int) {
        if(!musicList.value.isNullOrEmpty()) {
            if (pos >= musicList.value!!.size) {
                _musicPosition.postValue(0)
                clickMusicItem(musicList.value!![0].music, 0)
            } else {
                if (_musicPosition.value != pos) {
                    _musicPosition.postValue(pos)
                    clickMusicItem(musicList.value!![pos].music, pos)
                }
            }
        }
    }

    private fun musicStateCheck() {
        while (mediaPlayer.isPlaying) {
            val curpos = mediaPlayer.currentPosition
            val duration = mediaPlayer.duration

            Log.d("test", "duration is $duration , $curpos")

            if (curpos >= duration - 20 && curpos != 0 && duration != 0) {
                setPosition(musicPosition.value!!.plus(1))
                break
            }
        }
    }

    // close player, music stop
    fun closePlayer() {
        _showPlayer.value = false
        stopMusic()
    }
}