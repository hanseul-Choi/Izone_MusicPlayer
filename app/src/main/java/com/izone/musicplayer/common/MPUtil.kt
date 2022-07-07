package com.izone.musicplayer.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object MPUtil {
    fun hasNetwork(ctx: Context): Boolean {
        val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activityNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return(activityNetwork != null) && activityNetwork.isConnected
    }
}