//package com.izone.musicplayer.view
//
//import android.util.Log
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
////import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
//import com.bumptech.glide.Glide
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
//import com.izone.musicplayer.MPConst.STORAGE_URL
//import com.izone.musicplayer.R
////import com.izone.musicplayer.databinding.ActivityMainBinding
////import com.izone.musicplayer.databinding.FragmentMiniplayerBinding
//import com.izone.musicplayer.model.MusicItems
//import com.izone.musicplayer.viewmodel.MainViewModel
//
//class MiniPlayerFragment(private val amBinding: ActivityMainBinding) : Fragment() {
//
//    lateinit var fMbinding: FragmentMiniplayerBinding
//
//    lateinit var list: List<MusicItems>
//    private var pos = 0
//    private val viewModel : MainViewModel by activityViewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        fMbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_miniplayer, container,false)
//
//        setFragmentViewModelListener()
//        setButtonClickListener()
//
//        return fMbinding.root
//    }
//
//    private fun setFragmentViewModelListener() {
//        viewModel.musicList.observe(requireActivity()) {
//            list = it.toMutableList()
//        }
//
//        Log.d("test", "musicposition? ${viewModel.musicPosition.value}")
//
//        viewModel.musicPosition.observe(viewLifecycleOwner) {
//            pos = it
//
//            Log.d("test", "position observe : $pos")
//
//            //position을 건들였을 때는 무조건 play 상태
//            fMbinding.fmIvPlay.visibility = View.INVISIBLE
//            fMbinding.fmIvStop.visibility = View.VISIBLE
//
//            fMbinding.fmTvTitle.text = list[pos].title
//            fMbinding.fmTvSinger.text = list[pos].singer
//
//            var storage: FirebaseStorage = FirebaseStorage.getInstance(STORAGE_URL)
//            var storageRef: StorageReference = storage.reference
//
//            //set image list[pos].album
//
//            storageRef.child(list[pos].album).downloadUrl.addOnCompleteListener { task ->
//                if(task.isSuccessful) {
//                    Glide.with(this).load(task.result).into(fMbinding.fmIvAlbum)
//                }
//            }
//
//            //set music
//            storageRef.child(list[pos].music).downloadUrl.addOnSuccessListener { uri ->
//                viewModel.setMusic(uri.toString())
//                viewModel.playMusic()
//            }
//        }
//    }
//
//    private fun setButtonClickListener() {
//        fMbinding.fmIvPlay.setOnClickListener {
//            fMbinding.fmIvPlay.visibility = View.INVISIBLE
//            fMbinding.fmIvStop.visibility = View.VISIBLE
//
//            viewModel.playMusic()
//        }
//
//        fMbinding.fmIvStop.setOnClickListener {
//            fMbinding.fmIvPlay.visibility = View.VISIBLE
//            fMbinding.fmIvStop.visibility = View.INVISIBLE
//
//            viewModel.stopMusic()
//        }
//
//        fMbinding.fmIvNext.setOnClickListener {
//            viewModel.setPosition(pos+1)
//        }
//
//        fMbinding.miniplayerClose.setOnClickListener {
//            viewModel.stopMusic()
//            amBinding.amFlMiniplayer.visibility = View.GONE
//        }
//    }
//}