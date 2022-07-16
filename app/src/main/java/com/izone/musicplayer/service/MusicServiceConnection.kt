package com.izone.musicplayer.service

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

object MusicServiceConnection : ServiceConnection {
    var musicService = MusicService()
    // bind 여부 확인하여 Service에 접근할 수 있게 하기 위함
    var mBounds = false

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MusicService.MusicBinder
        musicService = binder.getService()
        mBounds = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        mBounds = false
    }
}