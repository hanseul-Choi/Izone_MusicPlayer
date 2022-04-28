package com.izone.musicplayer.network

import com.izone.musicplayer.model.MusicItems
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MusicApi {
    @GET("music/izone.json")
    suspend fun getIzone(

    ): Response<List<MusicItems>>

    @GET("music/bts.json")
    suspend fun getBts(

    ): Response<List<MusicItems>>

    @GET("music/ohmygirl.json")
    suspend fun getOhmygirl(

    ): Response<List<MusicItems>>
}