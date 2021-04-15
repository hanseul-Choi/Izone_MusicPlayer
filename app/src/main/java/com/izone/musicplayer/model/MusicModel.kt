package com.izone.musicplayer.model

import com.google.gson.annotations.SerializedName

data class MusicModel (
    @SerializedName("music")
    val musicList: List<MusicItems>
)

data class MusicItems (
    @SerializedName("album")
    val album: String,

    @SerializedName("music")
    val music: String,

    @SerializedName("singer")
    val singer: String,

    @SerializedName("title")
    val title: String
)