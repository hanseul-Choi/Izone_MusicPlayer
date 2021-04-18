package com.izone.musicplayer.fragment

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

class MiniPlayerFragment : Fragment() {

    lateinit var fMbinding: FragmentMiniplayerBinding
    private val fragmentViewModel: FragmentViewModel by activityViewModels()
    private val fireBaseUri = "gs://musicplayer-e17d2.appspot.com/"

    lateinit var list: List<MusicItems>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fMbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_miniplayer, container,false)

        setFragmentViewModelListener()

        return fMbinding.root
    }

    fun setFragmentViewModelListener() {
        fragmentViewModel.muisicListRepositories.observe(requireActivity()) {
            list = it.toMutableList()
        }

        fragmentViewModel.musicPosition.observe(viewLifecycleOwner) {
            fMbinding.fmTvTitle.text = list[it].title
            fMbinding.fmTvSinger.text = list[it].singer

            Log.d("check list", "list album is ${list[it].album}")

            var storage: FirebaseStorage = FirebaseStorage.getInstance(fireBaseUri)
            var storageRef: StorageReference = storage.getReference()
            storageRef.child(list[it].album).downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this).load(uri).into(fMbinding.fmIvAlbum)
            }
        }
    }
}