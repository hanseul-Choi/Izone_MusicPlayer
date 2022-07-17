package com.izone.musicplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.repository.music.MusicControlDao
import com.izone.musicplayer.repository.storage.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MiniPlayerViewModel @Inject constructor(
    private val storageRepository: StorageRepository,
    private val musicControlDao: MusicControlDao
    ) : ViewModel() {

    private val _curMusic = MutableLiveData<MusicItems>()
    val curMusic : LiveData<MusicItems> = _curMusic

}