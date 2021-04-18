package com.izone.musicplayer.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.FragmentMiniplayerBinding

class MiniPlayerFragment : Fragment() {

    lateinit var fMbinding: FragmentMiniplayerBinding
    private val fragmentViewModel: FragmentViewModel by viewModels()

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
        fragmentViewModel.muisicListRepositories.observe(viewLifecycleOwner) {
            fMbinding.fmTvTitle.text = it[0].title

            Log.d("listener", "check ${it[0].title}")
        }
    }
}