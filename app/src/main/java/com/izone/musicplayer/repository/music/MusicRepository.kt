package com.izone.musicplayer.repository.music

import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val musicRemoteData: MusicRemoteDataSource
) {
    suspend fun getIzoneRepository() = musicRemoteData.getIzone()
    suspend fun getBtsRepository() = musicRemoteData.getBts()
    suspend fun getOhmygirlRepository() = musicRemoteData.getOhmygirl()
}
