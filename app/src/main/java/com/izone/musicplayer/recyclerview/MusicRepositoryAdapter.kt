package com.izone.musicplayer.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.izone.musicplayer.R
import com.izone.musicplayer.model.MusicItems

class MusicRepositoryAdapter(private var repositories: List<MusicItems>) : RecyclerView.Adapter<MusicRepositoryItemHolder>() {
    interface OnMusicClickListener {
        fun onItemClick(position: Int)
    }

    var listener: OnMusicClickListener? = null

    override fun getItemCount(): Int = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicRepositoryItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)

        return MusicRepositoryItemHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MusicRepositoryItemHolder, position: Int) {
        holder.bind(repositories[position])
    }

    //update
    fun update(updated: List<MusicItems>) {
        val diff = MusicRepositoryDiffCallback(repositories, updated)
        val diffResult = DiffUtil.calculateDiff(diff)
        repositories = updated
        diffResult.dispatchUpdatesTo(this)
    }
}