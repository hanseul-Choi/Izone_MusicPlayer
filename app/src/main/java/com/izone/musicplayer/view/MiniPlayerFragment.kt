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
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.viewmodel.MainViewModel
import com.izone.musicplayer.viewmodel.MiniPlayerViewModel
import com.izone.musicplayer.viewmodel.ViewModelFactory

class MiniPlayerFragment : Fragment() {

    lateinit var fMbinding: FragmentMiniplayerBinding

    lateinit var list: List<MusicItems>
    private var pos = 0
    private val viewModel: MiniPlayerViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fMbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_miniplayer, container,false)

        setFragmentViewModelListener()

        fMbinding.lifecycleOwner = viewLifecycleOwner
        fMbinding.viewModel = mainViewModel

        return fMbinding.root
    }

    private fun setFragmentViewModelListener() {
        mainViewModel.musicList.observe(requireActivity()) {
            list = it.toMutableList()
            viewModel.getMusicItem(list[0].music)
        }

        mainViewModel.musicPosition.observe(viewLifecycleOwner) {
            pos = it
        }
    }
}