package com.izone.musicplayer.network

object MusicService {
    private const val BASE_URL = "https://izonemusicplayer-ccd40-default-rtdb.firebaseio.com/"

    val client = BaseService().getClient(BASE_URL)?.create(MusicApi::class.java)
}