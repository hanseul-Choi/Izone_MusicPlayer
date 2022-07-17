package com.izone.musicplayer.repository.music

import com.izone.musicplayer.service.bind.MusicServiceConnection
import javax.inject.Inject

class MusicControlDaoImpl @Inject constructor() : MusicControlDao {
    override fun setMusic(uri: String) {
        MusicServiceConnection.musicService.setMusic(uri)
    }

    override fun playMusic() {
        MusicServiceConnection.musicService.playMusic()
    }

    override fun stopMusic() {
        MusicServiceConnection.musicService.stopMusic()
    }
}