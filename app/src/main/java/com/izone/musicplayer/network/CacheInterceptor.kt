package com.izone.musicplayer.network

import com.izone.musicplayer.MPApplication
import com.izone.musicplayer.common.MPConst
import com.izone.musicplayer.common.MPUtil
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient

object CacheInterceptor {
    private val onlineInterceptor = Interceptor { chain ->
        chain
            .proceed(chain.request())
            .newBuilder()
            .header("Cache-Control", "public, max-age=${MPConst.ONLINE_CACHE_AGE}")
            .removeHeader("Pragma")
            .build()
    }

    private val offlineInterceptor = Interceptor { chain ->
        if(!MPUtil.hasNetwork(MPApplication.context)) {
            chain
                .request()
                .newBuilder()
                .header("Cache-Control","public, only-if-cached, max-stale=${MPConst.OFFLINE_CACHE_AGE}")
                .removeHeader("Pragma")
                .build()
        }

        chain.proceed(chain.request())
    }

    private val cache = Cache(MPApplication.context.cacheDir, MPConst.CACHE_SIZE)

    val okHttpClient: OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(offlineInterceptor)
            .addInterceptor(onlineInterceptor)
            .cache(cache)
            .build()
}