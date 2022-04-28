package com.izone.musicplayer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
//import androidx.databinding.DataBindingUtil
import com.izone.musicplayer.R
//import com.izone.musicplayer.databinding.ActivityMainBinding
//import com.izone.musicplayer.recyclerview.MusicRepositoryAdapter
import com.izone.musicplayer.repository.MusicRepository
import com.izone.musicplayer.viewmodel.MainViewModel
import com.izone.musicplayer.viewmodel.MainViewModelFactory
import com.izone.musicplayer.widget.MainScreen

/**
 * list
- Compose 변경
- Navigation 추가
- UI 및 UX 개선
- Warning 제거
 */

class MainActivity : AppCompatActivity() {

    //data binding
//    private lateinit var aMBinding: ActivityMainBinding

    //viewModel & Adpater
    private val viewModelFactory: MainViewModelFactory = MainViewModelFactory(MusicRepository())
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

//    lateinit var mMusicRepositoryAdapter: MusicRepositoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.requestIzoneRepositories()
//        initDataBinding()
//        initSpinnerSet()
//        setAdapter()
//        setFragment()

        setContent {
            MainScreen(viewModel = viewModel)
        }
    }

//    private fun initDataBinding() {
        //binding
//        aMBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        aMBinding.lifecycleOwner = this
//        aMBinding.viewModel = viewModel
    }

//    private fun setAdapter() {

//        mMusicRepositoryAdapter = MusicRepositoryAdapter(this)

//        mMusicRepositoryAdapter.setItemListener(object : MusicRepositoryAdapter.OnMusicClickListener {
//            override fun onItemClick(position: Int) {
//                viewModel.setPosition(position)
//                viewModel.playMusic()
//                aMBinding.amFlMiniplayer.visibility = View.VISIBLE
//            }
//        })

//        aMBinding.amRvAlbumList.adapter = mMusicRepositoryAdapter
//        aMBinding.adapter = mMusicRepositoryAdapter
//
//        aMBinding.amRvAlbumList.viewTreeObserver.addOnGlobalLayoutListener {
//        }
//    }

//    private fun initSpinnerSet() {
//        //spinner set
//        val items = resources.getStringArray(R.array.singer)
//
//        aMBinding.amSSingerList.adapter =
//            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items)
//
//        aMBinding.amSSingerList.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                    showProgress()
//
//                    when (p2) {
//                        0 -> {
//                            //izone
//                            viewModel.requestIzoneRepositories()
//                        }
//                        1 -> {
//                            //omg
//                            viewModel.requestOhmygirlRepositories()
//                        }
//                        2 -> {
//                            //bts
//                            viewModel.requestBtsRepositories()
//                        }
//                    }
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {
//                }
//            }
//    }
//
//    private fun setFragment() {
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.am_fl_miniplayer, MiniPlayerFragment(amBinding = aMBinding))
//            .commit()
//    }
//
//
//    fun showProgress() {
//        aMBinding.amClProgressBarLayout.visibility = View.VISIBLE
//    }
//
//    fun disableProgress() {
//        aMBinding.amClProgressBarLayout.visibility = View.GONE
//    }
//}