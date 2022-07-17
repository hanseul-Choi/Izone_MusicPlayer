package com.izone.musicplayer.service.foreground

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.izone.musicplayer.R
import com.izone.musicplayer.service.foreground.ServiceActionConst.CHANNEL_ID
import com.izone.musicplayer.view.MainActivity

object MusicNotification {

    fun createNotification(context: Context) : Notification {
        val notificationIntent = Intent(context, MainActivity::class.java)

        notificationIntent.apply {
            action = ServiceActionConst.MAIN
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText("My Music")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true) // true 일경우 알림 리스트에서 클릭하거나 좌우로 드래그해도 사라지지 않음
//            .addAction(NotificationCompat.Action(android.R.drawable.ic_media_previous,
//                "Prev", prevPendingIntent))
//            .addAction(NotificationCompat.Action(android.R.drawable.ic_media_play,
//                "Play", playPendingIntent))
//            .addAction(NotificationCompat.Action(android.R.drawable.ic_media_next,
//                "Next", nextPendingIntent))
//            .setContentIntent(pendingIntent)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "IZONE_MUSIC_CHANNEL",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }

        return notification
    }
}