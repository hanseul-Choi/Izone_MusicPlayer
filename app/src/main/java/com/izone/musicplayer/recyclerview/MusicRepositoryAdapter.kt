package com.izone.musicplayer.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.izone.musicplayer.databinding.ItemAlbumBinding
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.view.MainActivity
import com.izone.musicplayer.viewmodel.MainViewModel

class MusicRepositoryAdapter(private val viewModel: MainViewModel)
    : ListAdapter<MusicItems, MusicRepositoryAdapter.MusicViewHolder>(MusicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MusicViewHolder(private val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(musicItems: MusicItems) {
            binding.viewModel = viewModel
            binding.musicItems = musicItems
            binding.executePendingBindings()
        }
    }
}

class MusicDiffCallback : DiffUtil.ItemCallback<MusicItems>() {
    override fun areItemsTheSame(oldItem: MusicItems, newItem: MusicItems): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MusicItems, newItem: MusicItems): Boolean {
        return oldItem == newItem
    }

}