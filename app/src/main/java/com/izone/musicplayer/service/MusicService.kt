package com.izone.musicplayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.izone.musicplayer.model.MusicItems
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

// BindService를 이용하여 Activity와 통신하여 MediaPlayer를 동작시키자 (onBind + onStartCommand)
@AndroidEntryPoint
class MusicService : Service() {
    inner class MusicBinder: Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private val musicBinder by lazy {
        MusicBinder()
    }

    override fun onBind(intent: Intent?): IBinder {
        return musicBinder
    }

    // music list data
    val musicList = MutableLiveData<List<MusicItems>>()

    private val _musicPosition = MutableLiveData<Int>()
    val musicPosition: LiveData<Int> = _musicPosition

    private val mediaPlayer by lazy {
        MediaPlayer()
    }

    // music player data
    private val job = CoroutineScope(Dispatchers.Default)

    // setting music data
    fun setMusic(uri: String) {
        mediaPlayer.apply {
            reset()
            setDataSource(uri)
            prepare()
            start()
        }
    }

    // play music
    fun playMusic() {
        if(!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }

        if(!job.isActive) {
            job.launch {
                checkMusicState()
            }
        }
    }

    // stop music
    fun stopMusic() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.apply {
                pause()
            }
        }

        if (job.isActive) job.cancel()
    }

    private fun checkMusicState() {
        while(mediaPlayer.isPlaying) {
            val curPos = mediaPlayer.currentPosition
            val duration = mediaPlayer.duration

            Log.d("test", "duration is $duration , $curPos")

            if (curPos >= duration - 20 && curPos != 0 && duration != 0) {
                checkPosition(_musicPosition.value!!.plus(1))
                break
            }
        }
    }

    // position 확인 작업
    private fun checkPosition(pos: Int) {
        if (!musicList.value.isNullOrEmpty()) {
            if (pos >= musicList.value!!.size) {
                _musicPosition.postValue(0)
            } else {
                if (_musicPosition.value != pos) {
                    _musicPosition.postValue(pos)
                }
            }
        }
    }
}