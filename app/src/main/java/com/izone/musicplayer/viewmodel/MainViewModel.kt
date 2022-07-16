package com.izone.musicplayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.repository.music.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val musicRepository: MusicRepository
) : ViewModel() {

    lateinit var musicItems: List<MusicItems>

    suspend fun requestIzoneRepositories() : List<MusicItems> {
        musicItems = withContext(viewModelScope.coroutineContext) {
            musicRepository.getIzoneRepository()
        }

        return musicItems
    }

    suspend fun requestBtsRepositories() : List<MusicItems> {

        musicItems = withContext(viewModelScope.coroutineContext) {
            musicRepository.getBtsRepository()
        }

        return musicItems
    }

    suspend fun requestOhmygirlRepositories() : List<MusicItems> {
        musicItems = withContext(viewModelScope.coroutineContext) {
            musicRepository.getOhmygirlRepository()
        }

        return musicItems
    }
}