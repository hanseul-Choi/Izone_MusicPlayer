package com.izone.musicplayer.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.izone.musicplayer.model.MusicItems

class MusicRepositoryDiffCallback(private val oldList: List<MusicItems>, private val newList: List<MusicItems>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].title == newList[newItemPosition].title
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition].title == newList[newItemPosition].title
}