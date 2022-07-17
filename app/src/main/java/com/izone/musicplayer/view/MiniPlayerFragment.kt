package com.izone.musicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.FragmentMiniplayerBinding
import com.izone.musicplayer.service.MusicServiceConnection
import com.izone.musicplayer.viewmodel.MainViewModel
import com.izone.musicplayer.viewmodel.MiniPlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MiniPlayerFragment : Fragment() {

    lateinit var fMbinding: FragmentMiniplayerBinding
    private val viewModel: MiniPlayerViewModel by viewModels()
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fMbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_miniplayer, container,false)

        fMbinding.lifecycleOwner = viewLifecycleOwner
        fMbinding.mainviewmodel = mainViewModel

        return fMbinding.root
    }
}