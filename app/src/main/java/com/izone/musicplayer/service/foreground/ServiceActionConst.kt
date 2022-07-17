package com.izone.musicplayer.service.foreground

import com.izone.musicplayer.MPApplication

object ServiceActionConst {
    private val prefix = MPApplication.context.packageName + "."
    val START_FOREGROUND = prefix + "startforeground"
    val STOP_FOREGROUND = prefix + "stopforeground"
    val MAIN = prefix + "main"
    val PREV = prefix + "prev"
    val NEXT = prefix + "next"
    val PLAY = prefix + "play"

    const val MUSIC_NOTIFICATION_ID = 20
    const val CHANNEL_ID = "izone_player_channel"
}