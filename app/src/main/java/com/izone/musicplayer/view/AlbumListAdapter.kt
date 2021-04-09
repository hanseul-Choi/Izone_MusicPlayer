package com.izone.musicplayer.view

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.izone.musicplayer.R
import com.izone.musicplayer.model.AlbumList
import org.w3c.dom.Text

data class AlbumListAdapter (
    private var repositories: List<AlbumList>,
    val itemClick: (AlbumList) -> Unit
) : RecyclerView.Adapter<AlbumListAdapter.AlbumListItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListItemHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AlbumListItemHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    //Holder
    inner class AlbumListItemHolder(view: View, itemClick: (AlbumList) -> Unit) : RecyclerView.ViewHolder(view) {
        private val img_album: ImageView = view.findViewById(R.id.ia_iv_album)
        private val tv_title: TextView = view.findViewById(R.id.ia_tv_title)
        private val tv_singer: TextView = view.findViewById(R.id.ia_tv_sub_title)
    }
}