package com.izone.musicplayer

import com.izone.musicplayer.network.MusicApi

object ServiceLocator {
    private var musicApi: MusicApi? = null

    fun provideMusicApi(): MusicApi {
        return musicApi ?: kotlin.run {
            MusicApi.create().also {
                musicApi = it
            }
        }
    }

}