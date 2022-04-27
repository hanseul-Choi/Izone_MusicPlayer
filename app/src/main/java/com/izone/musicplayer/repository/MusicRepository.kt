package com.izone.musicplayer.repository

import com.izone.musicplayer.network.MusicService

class MusicRepository {
    private val musicClient = MusicService.client

    suspend fun getIzoneRepository() = musicClient?.getIzone()
    suspend fun getBtsRepository() = musicClient?.getBts()
    suspend fun getOhmygirlRepository() = musicClient?.getOhmygirl()
}
