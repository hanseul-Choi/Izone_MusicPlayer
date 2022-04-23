package com.izone.musicplayer

object MPConst {
    const val BASE_URL = "https://izonemusicplayer-ccd40-default-rtdb.firebaseio.com/"
    const val STORAGE_URL = "gs://izonemusicplayer-ccd40.appspot.com/"

    // Network Cache
    const val ONLINE_CACHE_AGE = 120 // 120 second
    const val OFFLINE_CACHE_AGE = 60 * 60 * 24 * 30 // 30 days
    const val CACHE_SIZE = (10 * 1024 * 1024).toLong() // 10 MB
}