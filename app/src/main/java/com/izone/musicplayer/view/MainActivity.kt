package com.izone.musicplayer.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.izone.musicplayer.R
import com.izone.musicplayer.common.Event
import com.izone.musicplayer.databinding.ActivityMainBinding
import com.izone.musicplayer.recyclerview.MusicRepositoryAdapter
import com.izone.musicplayer.viewmodel.MainViewModel
import com.izone.musicplayer.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * list
 * 이미지 네트워크 처리 방식 수정
 * 이미지 받아오는 작업
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //data binding
    private lateinit var aMBinding: ActivityMainBinding

    //viewModel & Adpater
    private val viewModel: MainViewModel by viewModels()

//    @Inject
//    lateinit var musicAdapter: MusicRepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initSpinnerSet()
        setAdapter()
        setFragment()
        setEvent()
    }

    private fun initDataBinding() {
        //binding
        aMBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        aMBinding.lifecycleOwner = this
        aMBinding.viewModel = viewModel
    }

    private fun setAdapter() {
        val musicAdapter = MusicRepositoryAdapter(viewModel)
        aMBinding.amRvAlbumList.adapter = musicAdapter

        viewModel.musicList.observe(this) {
            musicAdapter.submitList(it)
            musicAdapter.notifyDataSetChanged()
        }
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
                            viewModel.initMusicView()

                            //izone
                            viewModel.requestIzoneRepositories()
                        }
                        1 -> {
                            viewModel.initMusicView()

                            //omg
                            viewModel.requestOhmygirlRepositories()
                        }
                        2 -> {
                            viewModel.initMusicView()

                            //bts
                            viewModel.requestBtsRepositories()
                        }
                    }
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

    private fun setEvent() {
        // music item click event
        viewModel.musicEvent.observe(this, Event.EventObserver {
            viewModel.setMusic(it)
        })
    }
}