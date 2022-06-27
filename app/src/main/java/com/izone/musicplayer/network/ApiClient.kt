package com.izone.musicplayer.network

import android.net.Uri
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.izone.musicplayer.MPConst
import com.izone.musicplayer.model.MusicItems
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {
    @GET("music/izone.json")
    suspend fun getIzone(): List<MusicItems>

    @GET("music/bts.json")
    suspend fun getBts(): List<MusicItems>

    @GET("music/ohmygirl.json")
    suspend fun getOhmygirl(): List<MusicItems>

    companion object {
        fun create(): ApiClient {
            return Retrofit.Builder()
                .baseUrl(MPConst.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}