package com.izone.musicplayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.repository.storage.StorageListener
import com.izone.musicplayer.repository.storage.StorageRepository
import com.izone.musicplayer.service.foreground.MusicNotification
import com.izone.musicplayer.service.foreground.ServiceActionConst
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

// BindService를 이용하여 Activity와 통신하여 MediaPlayer를 동작시키자 (onBind + onStartCommand)

@AndroidEntryPoint
class MusicService : Service() {

    @Inject
    lateinit var storageRepository: StorageRepository

    inner class MusicBinder: Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private val musicBinder by lazy {
        MusicBinder()
    }

    // music list data
    val musicList = MutableLiveData<List<MusicItems>>()
    var musicPosition = MutableLiveData<Int>(0)

    private val mediaPlayer by lazy {
        MediaPlayer()
    }

    // music player data
    private val job = CoroutineScope(Dispatchers.Default)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            ServiceActionConst.START_FOREGROUND -> {
                startForegroundService()
            }
            ServiceActionConst.STOP_FOREGROUND -> {
                stopForegroundService()
            }
        }

        return START_STICKY
    }

    private fun startForegroundService() {
        val notification = MusicNotification.createNotification(this)
        startForeground(ServiceActionConst.MUSIC_NOTIFICATION_ID, notification)
    }

    private fun stopForegroundService() {
        stopForeground(true)
        stopSelf()
    }

    override fun onBind(intent: Intent): IBinder {
        return musicBinder
    }

    // setting music data
    fun setMusic(uriString: String) {
        storageRepository.getMusicItem(uriString, object : StorageListener {
            override fun onSuccess(uri: Uri) {
                mediaPlayer.apply {
                    reset()
                    setDataSource(uri.toString())
                    prepare()
                    start()
                }
            }

            override fun onFailed(e: Exception) {
            }
        })
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

    fun nextMusic() {
        closeMusic()

        musicPosition.value?.plus(1)?.let {
            checkPosition(it)
        }

        musicList.value?.let{ musics ->
            musicPosition.value?.let {
                setMusic(musics[it].music)
            }
        }
    }

    // close music
    fun closeMusic() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.apply {
                pause()
                reset()
            }
        }
    }

    private fun checkMusicState() {
        while(mediaPlayer.isPlaying) {
            val curPos = mediaPlayer.currentPosition
            val duration = mediaPlayer.duration

            Log.d("test", "duration is $duration , $curPos")

            if (curPos >= duration - 20 && curPos != 0 && duration != 0) {
                nextMusic()
                break
            }
        }
    }

    // position 확인 작업
    fun checkPosition(pos: Int) {
        if (!musicList.value.isNullOrEmpty()) {
            if (pos >= musicList.value!!.size) {
                musicPosition.value = 0
            } else {
                if (musicPosition.value != pos) {
                    musicPosition.value = pos
                }
            }
        }
    }
}