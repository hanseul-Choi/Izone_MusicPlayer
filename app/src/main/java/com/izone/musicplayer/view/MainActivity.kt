package com.izone.musicplayer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.ActivityMainBinding
import com.izone.musicplayer.recyclerview.MusicRepositoryAdapter
import com.izone.musicplayer.repository.MusicRepository
import com.izone.musicplayer.viewmodel.MainViewModel
import com.izone.musicplayer.viewmodel.ViewModelFactory

/**
 * list
- 아키텍처 변경, 비지니스는 모두 viewmodel로
- Compose 변경
- Navigation 추가
- UI 및 UX 개선
- Warning 제거
 */

class MainActivity : AppCompatActivity() {

    //data binding
    private lateinit var aMBinding: ActivityMainBinding

    //viewModel & Adpater
    private val viewModel: MainViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initSpinnerSet()
        setAdapter()
        setFragment()
    }

    private fun initDataBinding() {
        //binding
        aMBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        aMBinding.lifecycleOwner = this
        aMBinding.viewModel = viewModel
    }

    private fun setAdapter() {
        val mMusicRepositoryAdapter = MusicRepositoryAdapter(viewModel)
        aMBinding.amRvAlbumList.adapter = mMusicRepositoryAdapter

        viewModel.musicList.observe(this) {
            mMusicRepositoryAdapter.submitList(it)
            mMusicRepositoryAdapter.notifyDataSetChanged()
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
                    showProgress()

                    when (p2) {
                        0 -> {
                            //izone
                            viewModel.requestIzoneRepositories()
                        }
                        1 -> {
                            //omg
                            viewModel.requestOhmygirlRepositories()
                        }
                        2 -> {
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
            .add(R.id.am_fl_miniplayer, MiniPlayerFragment(amBinding = aMBinding))
            .commit()
    }


    fun showProgress() {
        aMBinding.amClProgressBarLayout.visibility = View.VISIBLE
    }

    fun disableProgress() {
        aMBinding.amClProgressBarLayout.visibility = View.GONE
    }
}