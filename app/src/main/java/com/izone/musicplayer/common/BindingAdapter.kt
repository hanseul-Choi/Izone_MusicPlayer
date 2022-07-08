package com.izone.musicplayer.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

@BindingAdapter("bind:storageImg")
fun bindStorageItem(imageView: ImageView, childLoc: String?) {
    childLoc?.let {
        Glide.with(imageView)
            .load(FirebaseStorage.getInstance().getReference(childLoc))
            .into(imageView)
    }
}
