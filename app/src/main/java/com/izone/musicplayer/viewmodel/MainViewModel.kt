package com.izone.musicplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.repository.music.MusicControlDao
import com.izone.musicplayer.repository.music.MusicRepository
import com.izone.musicplayer.service.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val musicRepository: MusicRepository,
    private val musicControlDao: MusicControlDao
) : ViewModel() {

    lateinit var musicItems: List<MusicItems>

    private val _isShowMiniPlayer = MutableLiveData<Boolean>()
    val isShowMiniPlayer: LiveData<Boolean> = _isShowMiniPlayer

    private val _curMusic = MutableLiveData<MusicItems>()
    val curMusic: LiveData<MusicItems> = _curMusic

    private val _isPlay = MutableLiveData<Boolean>()
    val isPlay: LiveData<Boolean> = _isPlay

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

    fun onMusicItemClick(uri: String, pos: Int) {
        musicControlDao.setMusic(uri)
        musicControlDao.playMusic()

        MusicServiceConnection.musicService.musicPosition.value = pos
        _curMusic.value = musicItems[pos]

        // miniplayer가 보여지는지 확인해야 할 것 같음
        openMiniPlayer()
        _isPlay.value = true
    }

    fun closeMiniPlayer() {
        _isShowMiniPlayer.value = false
        MusicServiceConnection.musicService.closeMusic()
        _isPlay.value = false
    }

    fun openMiniPlayer() {
        _isShowMiniPlayer.value = true
    }

    fun nextButtonClick() {
        MusicServiceConnection.musicService.nextMusic()

        MusicServiceConnection.musicService.musicPosition.value?.let {
            _curMusic.value = musicItems[it]
        }
        _isPlay.value = true
    }

    fun playButtonClick() {
        musicControlDao.playMusic()
        _isPlay.value = true
    }

    fun stopButtonClick() {
        musicControlDao.stopMusic()
        _isPlay.value = false
    }
}