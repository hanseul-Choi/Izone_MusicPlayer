package com.izone.musicplayer.network

import com.izone.musicplayer.MPConst.BASE_URL

object MusicService {
    val client = BaseService().getClient(BASE_URL)?.create(MusicApi::class.java)
}