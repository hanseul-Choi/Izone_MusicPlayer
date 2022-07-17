package com.izone.musicplayer.repository.music

interface MusicControlDao {
    fun setMusic(uri: String)
    fun playMusic()
    fun stopMusic()
}