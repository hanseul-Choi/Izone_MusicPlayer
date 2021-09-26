package com.izone.musicplayer.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izone.musicplayer.databinding.ItemAlbumBinding
import com.izone.musicplayer.model.MusicItems

class MusicRepositoryAdapter(private var repositories: List<MusicItems>) : RecyclerView.Adapter<MusicRepositoryItemHolder>() {
    interface OnMusicClickListener {
        fun onItemClick(position: Int)
    }

    var listener: OnMusicClickListener? = null

    fun setItemListener(itemListener: OnMusicClickListener) {
        listener = itemListener
    }

    override fun getItemCount(): Int = repositories.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicRepositoryItemHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MusicRepositoryItemHolder(listener, binding)
    }

    override fun onBindViewHolder(holder: MusicRepositoryItemHolder, position: Int) {
        holder.bind(repositories[position])
    }
}