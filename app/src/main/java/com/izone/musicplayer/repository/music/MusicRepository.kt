package com.izone.musicplayer.repository.music

class MusicRepository(
    private val musicRemoteData: MusicRemoteDataSource
) {
    suspend fun getIzoneRepository() = musicRemoteData.getIzone()
    suspend fun getBtsRepository() = musicRemoteData.getBts()
    suspend fun getOhmygirlRepository() = musicRemoteData.getOhmygirl()
}