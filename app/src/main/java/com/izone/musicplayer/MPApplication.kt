package com.izone.musicplayer

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MPApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak") // Application Class이기 때문에 메모리릭 걱정 x
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        context = this
    }
}