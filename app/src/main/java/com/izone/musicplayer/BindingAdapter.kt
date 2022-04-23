package com.izone.musicplayer

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.recyclerview.MusicRepositoryAdapter

@BindingAdapter("bind:item", "bind:listener")
fun bindItem(recyclerView : RecyclerView, musicList: List<MusicItems>?, itemListener: MusicRepositoryAdapter.OnMusicClickListener) {

    if(musicList != null) {
        val adapter: MusicRepositoryAdapter? = recyclerView.adapter as? MusicRepositoryAdapter
        adapter?.checkMusicList(musicList)

        adapter?.notifyDataSetChanged()
    }
}