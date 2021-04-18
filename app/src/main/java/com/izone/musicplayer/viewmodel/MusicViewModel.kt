package com.izone.musicplayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.model.MusicModel
import com.izone.musicplayer.repository.MusicRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private val _musicRepositories = MutableLiveData<List<MusicItems>>()
    val musicRepositories = _musicRepositories

    fun requestIzoneRepositories() {
        musicRepository.getIzoneRepository()?.enqueue(object : Callback<List<MusicItems>> {
            override fun onResponse(
                call: Call<List<MusicItems>>,
                response: Response<List<MusicItems>>
            ) {
                var value = response.body()
                _musicRepositories.postValue(value)
            }

            override fun onFailure(call: Call<List<MusicItems>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun requestBtsRepositories() {
        musicRepository.getBtsRepository()?.enqueue(object : Callback<List<MusicItems>> {
            override fun onResponse(
                call: Call<List<MusicItems>>,
                response: Response<List<MusicItems>>
            ) {
                var value = response.body()
                _musicRepositories.postValue(value)
            }

            override fun onFailure(call: Call<List<MusicItems>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun requestOhmygirlRepositories() {
        musicRepository.getOhmygirlRepository()?.enqueue(object : Callback<List<MusicItems>> {
            override fun onResponse(
                call: Call<List<MusicItems>>,
                response: Response<List<MusicItems>>
            ) {
                var value = response.body()
                _musicRepositories.postValue(value)
            }

            override fun onFailure(call: Call<List<MusicItems>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}