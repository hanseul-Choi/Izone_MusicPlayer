package com.izone.musicplayer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.izone.musicplayer.ServiceLocator
import com.izone.musicplayer.repository.MusicRemoteDataSource
import com.izone.musicplayer.repository.MusicRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                val repository = MusicRepository(MusicRemoteDataSource(ServiceLocator.provideMusicApi()))
                MainViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel ${modelClass.name}")
            }
        }
    }
}
