package com.izone.musicplayer.repository

import com.izone.musicplayer.model.MusicItems

interface MusicDataSource {
    suspend fun getIzone(): List<MusicItems>
    suspend fun getBts(): List<MusicItems>
    suspend fun getOhmygirl(): List<MusicItems>
}