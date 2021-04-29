package com.izone.musicplayer.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.FragmentMiniplayerBinding
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.viewmodel.FragmentViewModel
import java.lang.Exception

class MiniPlayerFragment : Fragment() {

    lateinit var fMbinding: FragmentMiniplayerBinding
    private val fragmentViewModel: FragmentViewModel by activityViewModels()
    private var fireBase_BaseUri = "gs://musicplayer-e17d2.appspot.com/"

    lateinit var list: List<MusicItems>
    private var pos = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fMbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_miniplayer, container,false)

        setFragmentViewModelListener()
        setButtonClickListener()

        return fMbinding.root
    }

    fun setFragmentViewModelListener() {
        fragmentViewModel.musicListRepositories.observe(requireActivity()) {
            list = it.toMutableList()
        }

        fragmentViewModel.musicPosition.observe(viewLifecycleOwner) {
            pos = it

            //position을 건들였을 때는 무조건 play 상태

            fMbinding.fmIvPlay.visibility = View.INVISIBLE
            fMbinding.fmIvStop.visibility = View.VISIBLE

            fMbinding.fmTvTitle.text = list[pos].title
            fMbinding.fmTvSinger.text = list[pos].singer

            var storage: FirebaseStorage = FirebaseStorage.getInstance(fireBase_BaseUri)
            var storageRef: StorageReference = storage.getReference()

            //set image
            storageRef.child(list[pos].album).downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this).load(uri).into(fMbinding.fmIvAlbum)
            }

            //set music
            storageRef.child(list[pos].music).downloadUrl.addOnSuccessListener { uri ->
                fragmentViewModel.setMusic(uri.toString())
            }
        }
    }

    fun setButtonClickListener() {
        fMbinding.fmIvPlay.setOnClickListener {
            fMbinding.fmIvPlay.visibility = View.INVISIBLE
            fMbinding.fmIvStop.visibility = View.VISIBLE

            fragmentViewModel.playMusic()
        }

        fMbinding.fmIvStop.setOnClickListener {
            fMbinding.fmIvPlay.visibility = View.VISIBLE
            fMbinding.fmIvStop.visibility = View.INVISIBLE

            fragmentViewModel.stopMusic()
        }

        fMbinding.fmIvNext.setOnClickListener {
            fragmentViewModel.setPosition(pos+1)
        }
    }
}