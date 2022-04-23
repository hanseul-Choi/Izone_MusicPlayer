package com.izone.musicplayer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseService {

    fun getClient(baseUrl: String): Retrofit? = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(CacheInterceptor.okHttpClient)
        .build()
}