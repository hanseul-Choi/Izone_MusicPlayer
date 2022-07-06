package com.izone.musicplayer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.izone.musicplayer.network.StorageApiClient.Companion.storageRef

//@BindingAdapter("bind:item", "bind:listener")
//fun bindItem(recyclerView : RecyclerView, musicList: List<MusicItems>?, itemListener: MusicRepositoryAdapter.OnMusicClickListener) {
//
//    if(musicList != null) {
//        val adapter: MusicRepositoryAdapter? = recyclerView.adapter as? MusicRepositoryAdapter
//        adapter?.checkMusicList(musicList)
//
//        adapter?.notifyDataSetChanged()
//    }
//}

@BindingAdapter("bind:storageImg")
fun bindStorageItem(imageView: ImageView, childLoc: String) {
    Glide.with(imageView)
        .load(FirebaseStorage.getInstance().getReference(childLoc))
        .into(imageView)
}
