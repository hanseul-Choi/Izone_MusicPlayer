package com.izone.musicplayer.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izone.musicplayer.R
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.model.MusicModel

class MusicRepositoryItemHolder(view: View, listener: MusicRepositoryAdapter.OnMusicClickListener?) : RecyclerView.ViewHolder(view) {
    private val iv_album: ImageView = view.findViewById(R.id.ia_iv_album)
    private val tv_title: TextView = view.findViewById(R.id.ia_tv_title)
    private val tv_singer: TextView = view.findViewById(R.id.ia_tv_sub_title)

    init {
        view.setOnClickListener {
            listener?.onItemClick(adapterPosition)
        }
    }

    fun bind(model: MusicItems) {
        model.run {
            Glide.with(itemView).load(album).into(iv_album)
            tv_title.text = title
            tv_singer.text = singer
        }
    }
}