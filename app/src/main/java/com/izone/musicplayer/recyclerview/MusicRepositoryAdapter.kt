package com.izone.musicplayer.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.izone.musicplayer.R
import com.izone.musicplayer.model.MusicItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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

    //customize
    fun update(updated: List<MusicItems>) {
        CoroutineScope(Dispatchers.Main).launch {
            val diffResult = async(Dispatchers.IO) {
                getDiffResult(updated)
            }
            repositories = updated
            diffResult.await().dispatchUpdatesTo(this@MusicRepositoryAdapter)
        }
    }

    private fun getDiffResult(updated: List<MusicItems>): DiffUtil.DiffResult {
        val diffCallback = MusicRepositoryDiffCallback(repositories, updated)
        return DiffUtil.calculateDiff(diffCallback)
    }
}