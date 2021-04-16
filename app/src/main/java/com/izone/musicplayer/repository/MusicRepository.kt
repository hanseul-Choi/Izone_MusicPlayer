package com.izone.musicplayer.repository

import com.izone.musicplayer.network.MusicService

class MusicRepository {
    private val musicClient = MusicService.client

    fun getIzoneRepository() = musicClient?.getIzone()
    fun getBtsRepository() = musicClient?.getBts()
    fun getOhmygirlRepository() = musicClient?.getOhmygirl()
}
