package com.izone.musicplayer.recyclerview

import android.graphics.Bitmap
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.izone.musicplayer.MPConst.STORAGE_URL
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.ItemAlbumBinding
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.view.MainActivity

class MusicRepositoryItemHolder(listener: MusicRepositoryAdapter.OnMusicClickListener?,
                                itemAlbumBinding: ItemAlbumBinding,
                                private val activity: MainActivity) :
    RecyclerView.ViewHolder(itemAlbumBinding.root) {

    private val iaBinding: ItemAlbumBinding = itemAlbumBinding

    init {
        iaBinding.root.setOnClickListener {
            Log.d("test","adapterposition : $adapterPosition")
            listener?.onItemClick(adapterPosition)
        }
    }

    fun bind(model: MusicItems) {
        model.run {
            var storage: FirebaseStorage = FirebaseStorage.getInstance(STORAGE_URL)
            var storageRef: StorageReference = storage.reference
            val start = System.currentTimeMillis()

            // show ProgressBar작업

            storageRef.child(album).downloadUrl.addOnSuccessListener {
                // 여기다가 ProgressBar dismiss를 하면 리스트만큼 불릴텐데? -> ViewModel LiveData를 활용
                activity.disableProgress()

                Glide
                    .with(itemView)
                    .asBitmap()
                    .override(80, 80)
                    .listener(object: RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val end = System.currentTimeMillis()
                            Log.e(
                                "Glide",
                                "Delay: " + (end - start).toString() + " ms"
                            )
                            iaBinding.iaTvTitle.text = title
                            iaBinding.iaTvSubTitle.text = singer
                            return false
                        }
                    })
                    .load(it)
                    .placeholder(R.drawable.place_img)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iaBinding.iaIvAlbum)
            }
        }
    }
}