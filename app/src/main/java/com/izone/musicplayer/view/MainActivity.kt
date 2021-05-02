package com.izone.musicplayer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.ActivityMainBinding
import com.izone.musicplayer.viewmodel.FragmentViewModel
import com.izone.musicplayer.model.MusicItems
import com.izone.musicplayer.recyclerview.MusicRepositoryAdapter
import com.izone.musicplayer.repository.MusicRepository
import com.izone.musicplayer.viewmodel.MusicViewModel
import com.izone.musicplayer.viewmodel.MusicViewModelFactory

class MainActivity : AppCompatActivity() {

    //data binding
    private lateinit var aMBinding: ActivityMainBinding

    //viewModel & Adpater
    private val viewModelFactory: MusicViewModelFactory = MusicViewModelFactory(MusicRepository())
    private val viewModel: MusicViewModel by viewModels { viewModelFactory }
    private val fragmentViewModel: FragmentViewModel by viewModels()
    private lateinit var mMusicRepositoryAdapter: MusicRepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initSpinnerSet()
        viewModelListener()
        setFragment()
    }

    fun initDataBinding() {
        //binding
        aMBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        aMBinding.lifecycleOwner = this
    }

    fun viewModelListener() {
        viewModel.musicRepositories.observe(this) {
            updateRepositories(it)
        }
    }

    fun updateRepositories(repos: List<MusicItems>) {
        if (::mMusicRepositoryAdapter.isInitialized && fragmentViewModel.musicListRepositories!!.value?.get(
                0
            )?.singer == repos[0].singer
        ) { //초기화되었고, list가 저장된 것과 같다면
            mMusicRepositoryAdapter.update(repos)
        } else {
            mMusicRepositoryAdapter = MusicRepositoryAdapter(repos).apply {
                listener = object : MusicRepositoryAdapter.OnMusicClickListener {
                    override fun onItemClick(position: Int) {
                        if (repos[0].singer != fragmentViewModel.musicListRepositories!!.value?.get(
                                0
                            )?.singer
                        ) {//list가 저장된 것과 다르다면
                            fragmentViewModel.setRepositories(repos)
                            aMBinding.amVSetmini.setHeight(300)
                        }
                        fragmentViewModel.setPosition(position)
                    }
                }
            }

            aMBinding.amRvAlbumList.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = mMusicRepositoryAdapter
            }
        }
    }

    fun initSpinnerSet() {
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

    fun setFragment() {
        supportFragmentManager.beginTransaction().add(R.id.am_fl_miniplayer, MiniPlayerFragment())
            .commit()
    }

    //extension
    fun View.setHeight(value: Int) {
        val lp = layoutParams
        lp?.let {
            lp.height = value
            layoutParams = lp
        }
    }
}