package com.izone.musicplayer.network

import com.izone.musicplayer.model.MusicModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
    @GET("music")
    suspend fun getIzone(
        @Query("q") query: String
    ): Response<MusicModel>
}