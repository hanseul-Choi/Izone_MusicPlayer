package com.izone.musicplayer.viewmodel

import android.util.Log
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izone.musicplayer.model.MusicItems
//import com.izone.musicplayer.recyclerview.MusicRepositoryAdapter
import com.izone.musicplayer.repository.MusicRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private val _musicList = MutableLiveData<List<MusicItems>>()
    val musicList = _musicList

    private val job = CoroutineScope(Dispatchers.Default)

    private var retrofitJob: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("error", "error : ${throwable.localizedMessage}")
    }

    fun requestIzoneRepositories() {
//        musicRepository.getIzoneRepository()?.enqueue(object : Callback<List<MusicItems>> {
//            override fun onResponse(
//                call: Call<List<MusicItems>>,
//                response: Response<List<MusicItems>>
//            ) {
//                response.body()?.let { value ->
//                    _musicList.postValue(value)
//                }
//            }
//
//            override fun onFailure(call: Call<List<MusicItems>>, t: Throwable) {
//            }
//        })

        retrofitJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = musicRepository.getIzoneRepository()

            response?.let { res ->
                if(res.isSuccessful) {
                    _musicList.postValue(response.body())
                }
            }
        }
    }

    fun requestBtsRepositories() {
        retrofitJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = musicRepository.getBtsRepository()

            response?.let { res ->
                if(res.isSuccessful) {
                    _musicList.postValue(response.body())
                }
            }
        }
    }

    fun requestOhmygirlRepositories() {
        retrofitJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = musicRepository.getOhmygirlRepository()

            response?.let { res ->
                if(res.isSuccessful) {
                    _musicList.postValue(response.body())
                }
            }
        }
    }

    private val mediaPlayer = MediaPlayer()

    // player pos
    private val _musicPosition = MutableLiveData<Int>()
    val musicPosition = _musicPosition

    fun setMusic(uri: String) {
        mediaPlayer.apply {
            reset()
            setDataSource(uri)
            prepare()
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