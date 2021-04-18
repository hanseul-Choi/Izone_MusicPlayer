package com.izone.musicplayer.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.izone.musicplayer.R
import com.izone.musicplayer.model.MusicItems

class MusicRepositoryItemHolder(view: View, listener: MusicRepositoryAdapter.OnMusicClickListener?) : RecyclerView.ViewHolder(view) {
    private val iv_album: ImageView = view.findViewById(R.id.ia_iv_album)
    private val tv_title: TextView = view.findViewById(R.id.ia_tv_title)
    private val tv_singer: TextView = view.findViewById(R.id.ia_tv_sub_title)
    private val fireBaseUri = "gs://musicplayer-e17d2.appspot.com/"

    init {
        view.setOnClickListener {
            listener?.onItemClick(adapterPosition)
        }
    }

    fun bind(model: MusicItems) {

        model.run {
            var storage: FirebaseStorage = FirebaseStorage.getInstance(fireBaseUri)
            var storageRef: StorageReference = storage.getReference()
            storageRef.child(album).downloadUrl.addOnSuccessListener {
                Glide.with(itemView).load(it).into(iv_album)
            }
            tv_title.text = title
            tv_singer.text = singer
        }
    }
}