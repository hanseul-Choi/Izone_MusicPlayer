package com.izone.musicplayer.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.izone.musicplayer.databinding.ItemAlbumBinding
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.view.MainActivity

//class MusicRepositoryAdapter(private val activity: MainActivity) : RecyclerView.Adapter<MusicRepositoryItemHolder>() {
//    init {
//        setHasStableIds(true) // 이미 생성된 객체의 ID를 불러서 재사용
//    }
//
//    interface OnMusicClickListener {
//        fun onItemClick(position: Int)
//    }
//
//    var listener: OnMusicClickListener? = null
//    var musicList: List<MusicItems> = listOf()
//
//    fun checkMusicList(changedMusicList: List<MusicItems>) {
//        musicList = changedMusicList
//    }
//
//    fun setItemListener(itemListener: OnMusicClickListener) {
//        listener = itemListener
//    }
//
//    override fun getItemCount(): Int = musicList.size
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicRepositoryItemHolder {
////        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
////
////        return MusicRepositoryItemHolder(listener, binding, activity)
////    }
//
//    override fun onBindViewHolder(holder: MusicRepositoryItemHolder, position: Int) {
//        holder.bind(musicList[position])
//    }
//
//    override fun getItemViewType(position: Int): Int { // Scroll시 데이터가 바뀌는 문제 개선
//        return position
//    }
//}