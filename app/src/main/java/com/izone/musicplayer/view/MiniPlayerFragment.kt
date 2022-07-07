package com.izone.musicplayer.view

import android.util.Log
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.ActivityMainBinding
import com.izone.musicplayer.databinding.FragmentMiniplayerBinding
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.viewmodel.MainViewModel
import com.izone.musicplayer.viewmodel.MiniPlayerViewModel
import com.izone.musicplayer.viewmodel.ViewModelFactory

class MiniPlayerFragment(private val amBinding: ActivityMainBinding) : Fragment() {

    lateinit var fMbinding: FragmentMiniplayerBinding

    lateinit var list: List<MusicItems>
    private var pos = 0
    private val viewModel: MiniPlayerViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private val mainViewModel : MainViewModel by activityViewModels()

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fMbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_miniplayer, container,false)

        setFragmentViewModelListener()
        setButtonClickListener()

        fMbinding.lifecycleOwner = viewLifecycleOwner
        fMbinding.viewModel = mainViewModel

        return fMbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.musicUri.observe(viewLifecycleOwner) {
            Log.d("test","music uri is $it")
        }
    }

    private fun setFragmentViewModelListener() {
        mainViewModel.musicList.observe(requireActivity()) {
            list = it.toMutableList()
            viewModel.getMusicItem(list[0].music)
        }

        mainViewModel.musicPosition.observe(viewLifecycleOwner) {
            pos = it

            Log.d("test", "position observe : $pos")

            //position을 건들였을 때는 무조건 play 상태
            fMbinding.fmIvPlay.visibility = View.INVISIBLE
            fMbinding.fmIvStop.visibility = View.VISIBLE

            fMbinding.fmTvTitle.text = list[pos].title
            fMbinding.fmTvSinger.text = list[pos].singer
        }
    }

    private fun setButtonClickListener() {
        fMbinding.fmIvPlay.setOnClickListener {
            fMbinding.fmIvPlay.visibility = View.INVISIBLE
            fMbinding.fmIvStop.visibility = View.VISIBLE

            mainViewModel.playMusic()
        }

        fMbinding.fmIvStop.setOnClickListener {
            fMbinding.fmIvPlay.visibility = View.VISIBLE
            fMbinding.fmIvStop.visibility = View.INVISIBLE

            mainViewModel.stopMusic()
        }

        fMbinding.fmIvNext.setOnClickListener {
            mainViewModel.setPosition(pos+1)
        }

        fMbinding.miniplayerClose.setOnClickListener {
            mainViewModel.stopMusic()
            amBinding.amFlMiniplayer.visibility = View.GONE
        }
    }
}