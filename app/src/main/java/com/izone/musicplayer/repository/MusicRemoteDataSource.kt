package com.izone.musicplayer.repository

import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.network.MusicApi

class MusicRemoteDataSource(private val api: MusicApi): MusicDataSource {
    override suspend fun getIzone(): List<MusicItems> {
        return api.getIzone()
    }

    override suspend fun getBts(): List<MusicItems> {
        return api.getBts()
    }

    override suspend fun getOhmygirl(): List<MusicItems> {
        return api.getOhmygirl()
    }
}