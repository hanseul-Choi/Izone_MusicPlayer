package com.izone.musicplayer.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.ActivityMainBinding
import com.izone.musicplayer.recyclerview.MusicRepositoryAdapter
import com.izone.musicplayer.service.MusicService
import com.izone.musicplayer.service.bind.MusicServiceConnection
import com.izone.musicplayer.service.bind.ServiceBindListener
import com.izone.musicplayer.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * list
 * 이미지 네트워크 처리 방식 수정
 * 이미지 받아오는 작업
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //data binding
    private lateinit var aMBinding: ActivityMainBinding

    //viewModel & Adapter
    private val viewModel: MainViewModel by viewModels()
    private val musicAdapter by lazy {
        MusicRepositoryAdapter(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        setAdapter()
        initSpinnerSet()
        setFragment()
    }

    private fun startMusicService() {
        Intent(this, MusicService::class.java).also { intent ->
            bindService(intent, MusicServiceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStart() {
        super.onStart()

        startMusicService()
    }

    override fun onStop() {
        super.onStop()

        if (MusicServiceConnection.mBounds) {
            unbindService(MusicServiceConnection)
            MusicServiceConnection.mBounds = false
        }
    }

    private fun initDataBinding() {
        //binding
        aMBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        aMBinding.lifecycleOwner = this
        aMBinding.viewModel = viewModel
    }

    private val serviceBindListener by lazy {
        object : ServiceBindListener {
            override fun firstServiceBind() {
                lifecycleScope.launch {
                    MusicServiceConnection.musicService.musicList.value =
                        viewModel.requestIzoneRepositories()
                    musicAdapter.submitList(MusicServiceConnection.musicService.musicList.value)
                    musicAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setAdapter() {
        aMBinding.amRvAlbumList.adapter = musicAdapter

        MusicServiceConnection.serviceBindListener = serviceBindListener
    }

    private fun initSpinnerSet() {
        //spinner set
        val items = resources.getStringArray(R.array.singer)

        aMBinding.amSSingerList.adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items)

        aMBinding.amSSingerList.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    when (p2) {
                        0 -> {
                            //izone
                            lifecycleScope.launch {
                                MusicServiceConnection.musicService.musicList.value =
                                    viewModel.requestIzoneRepositories()
                                musicAdapter.submitList(MusicServiceConnection.musicService.musicList.value)
                                musicAdapter.notifyDataSetChanged()
                            }
                        }
                        1 -> {
                            //omg
                            lifecycleScope.launch {
                                MusicServiceConnection.musicService.musicList.value =
                                    viewModel.requestOhmygirlRepositories()
                                musicAdapter.submitList(MusicServiceConnection.musicService.musicList.value)
                                musicAdapter.notifyDataSetChanged()
                            }
                        }
                        2 -> {
                            //bts
                            lifecycleScope.launch {
                                MusicServiceConnection.musicService.musicList.value =
                                    viewModel.requestBtsRepositories()
                                musicAdapter.submitList(MusicServiceConnection.musicService.musicList.value)
                                musicAdapter.notifyDataSetChanged()
                            }
                        }
                    }

                    viewModel.closeMiniPlayer()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }

    private fun setFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.am_fl_miniplayer, MiniPlayerFragment())
            .commit()
    }
}
