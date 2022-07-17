package com.izone.musicplayer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.izone.musicplayer.common.ServiceLocator
import com.izone.musicplayer.repository.music.MusicControlDaoImpl
import com.izone.musicplayer.repository.music.MusicRemoteDataSource
import com.izone.musicplayer.repository.music.MusicRepository
import com.izone.musicplayer.repository.storage.StorageRemoteDataSource
import com.izone.musicplayer.repository.storage.StorageRepository

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                val musicRepository =
                    MusicRepository(MusicRemoteDataSource(ServiceLocator.provideMusicApi()))

                MainViewModel(musicRepository, MusicControlDaoImpl()) as T
            }
            modelClass.isAssignableFrom(MiniPlayerViewModel::class.java) -> {
                val repository =
                    StorageRepository(StorageRemoteDataSource(ServiceLocator.provideStorageApi()))
                MiniPlayerViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel ${modelClass.name}")
            }
        }
    }
}
