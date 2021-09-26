package com.izone.musicplayer.recyclerview

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.izone.musicplayer.databinding.ItemAlbumBinding
import com.izone.musicplayer.model.MusicItems

class MusicRepositoryItemHolder(listener: MusicRepositoryAdapter.OnMusicClickListener?, itemAlbumBinding: ItemAlbumBinding) :
    RecyclerView.ViewHolder(itemAlbumBinding.root) {

    private val iaBinding: ItemAlbumBinding = itemAlbumBinding

    private val fireBaseUri = "gs://musicplayer-e17d2.appspot.com/"

    init {
        iaBinding.root.setOnClickListener {
            Log.d("test","adapterposition : $adapterPosition")
            listener?.onItemClick(adapterPosition)
        }
    }

    fun bind(model: MusicItems) {
        model.run {
            var storage: FirebaseStorage = FirebaseStorage.getInstance(fireBaseUri)
            var storageRef: StorageReference = storage.reference

            storageRef.child(album).downloadUrl.addOnSuccessListener {
                Glide.with(itemView).load(it).into(iaBinding.iaIvAlbum)
            }

            iaBinding.iaTvTitle.text = title
            iaBinding.iaTvSubTitle.text = singer
        }
    }
}