package com.izone.musicplayer.repository.music

import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.network.ApiClient

class MusicRemoteDataSource(private val apiClient: ApiClient): MusicDataSource {
    override suspend fun getIzone(): List<MusicItems> {
        return apiClient.getIzone()
    }

    override suspend fun getBts(): List<MusicItems> {
        return apiClient.getBts()
    }

    override suspend fun getOhmygirl(): List<MusicItems> {
        return apiClient.getOhmygirl()
    }
}