package com.izone.musicplayer.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izone.musicplayer.model.MusicItems

class FragmentViewModel : ViewModel() {
    // player list
    private val _musicListRepositories = MutableLiveData<List<MusicItems>>()
    val muisicListRepositories = _musicListRepositories

    // player pos
    private val _musicPosition = MutableLiveData<Int>()
    val musicPosition = _musicPosition

    // player state, false -> stop, true -> play
    private val _musicState = MutableLiveData<Boolean>()
    val musicState = _musicState

    fun setRepositories(musicList: List<MusicItems>) {
        _musicListRepositories.postValue(musicList)
    }

    fun setPosition(pos: Int) {
        _musicPosition.postValue(pos)
    }

    fun setState(state: Boolean) {
        _musicState.postValue(state)
    }
}