package com.izone.musicplayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.izone.musicplayer.repository.MusicRepository

class MusicViewModelFactory(private val musicRepository: MusicRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MusicRepository::class.java).newInstance(musicRepository)
    }
}