package com.izone.musicplayer.repository

import com.izone.musicplayer.network.MusicService

class MusicRepository {
    private val musicClient = MusicService.client

    fun getRepositories() = musicClient?.getIzone()
}
