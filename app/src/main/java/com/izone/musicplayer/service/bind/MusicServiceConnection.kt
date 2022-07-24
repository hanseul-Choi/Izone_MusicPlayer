package com.izone.musicplayer.service.bind

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import com.izone.musicplayer.service.MusicService

object MusicServiceConnection : ServiceConnection {
    var musicService = MusicService()

    // bind 여부 확인하여 Service에 접근할 수 있게 하기 위함
    var mBounds = false

    lateinit var serviceBindListener: ServiceBindListener

    // first init check
    private var isFirstConnect = true

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MusicService.MusicBinder
        musicService = binder.getService()
        if(isFirstConnect) {
            serviceBindListener.firstServiceBind()
            isFirstConnect = false
        }
        mBounds = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        mBounds = false
    }
}