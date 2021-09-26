package com.izone.musicplayer.network

import com.izone.musicplayer.model.MusicItems
import retrofit2.Call
import retrofit2.http.GET

interface MusicApi {
    @GET("music/izone.json")
    fun getIzone(

    ): Call<List<MusicItems>>

    @GET("music/bts.json")
    fun getBts(

    ): Call<List<MusicItems>>

    @GET("music/ohmygirl.json")
    fun getOhmygirl(

    ): Call<List<MusicItems>>
}