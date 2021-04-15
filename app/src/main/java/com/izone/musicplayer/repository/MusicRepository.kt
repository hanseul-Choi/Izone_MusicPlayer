package com.izone.musicplayer.repository

import android.app.DownloadManager
import com.izone.musicplayer.network.MusicService

class MusicRepository {
    private val musicClient = MusicService.client

    suspend fun getRepositories(query: String) = musicClient?.getIzone(query)
}
