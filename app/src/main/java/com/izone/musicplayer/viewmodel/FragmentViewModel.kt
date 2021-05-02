package com.izone.musicplayer.viewmodel

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import com.izone.musicplayer.model.MusicItems
import kotlinx.coroutines.*
import java.lang.Exception

class FragmentViewModel : ViewModel() {
    // player list
    private val _musicListRepositories = MutableLiveData<List<MusicItems>>()
    val musicListRepositories = _musicListRepositories

    // player pos
    private val _musicPosition = MutableLiveData<Int>()
    val musicPosition = _musicPosition

    // mediaplayer : lifecycle을 생각했을 때, MainActivity까지 살아있어야 할듯
    private val mediaPlayer = MediaPlayer()

    //coroutine
    private val job = Job()
    private val mediaScope = CoroutineScope(Dispatchers.IO + job)

    fun setRepositories(musicList: List<MusicItems>) {
        _musicListRepositories.value = musicList
        _musicListRepositories.postValue(musicList)
    }

    fun setPosition(pos: Int) {
        if (pos >= musicListRepositories.value!!.size) {
            _musicPosition.postValue(0)
        } else {
            _musicPosition.postValue(pos)
        }
    }

    fun setMusic(uri: String) {
        mediaPlayer.apply {
            reset()
            setDataSource(uri)
            prepare()
            playMusic()
        }

        ProcessUpdate()
    }

    fun playMusic() {
        mediaPlayer.apply {
            start()
        }

    }

    fun stopMusic() {
        mediaPlayer.apply {
            pause()
        }
    }

    fun ProcessUpdate() {
        mediaScope.launch {
            async(Dispatchers.IO) {
                while (mediaPlayer.isPlaying) {
                    var curpos = mediaPlayer.currentPosition
                    var duration = mediaPlayer.duration

                    Log.d("curpos", "curpos : $curpos , duration : $duration")

                    if (curpos >= duration - 20 && curpos != 0 && duration != 0) {
                        Log.d("pos value", "pos value is ${musicPosition.value!!.plus(1)}")
                        setPosition(musicPosition.value!!.plus(1))
                        break
                    }
                }
            }
        }
    }
}