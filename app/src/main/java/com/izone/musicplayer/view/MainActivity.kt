package com.izone.musicplayer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.izone.musicplayer.R
import com.izone.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //data binding
    private lateinit var aMBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initSpinnerSet()
        //recyclerview set
    }

    fun initDataBinding() {
        //binding
        aMBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        aMBinding.lifecycleOwner = this
        //aMBinding.singerListViewModel= viewModel
    }
    
    fun initSpinnerSet() {
        //spinner set
        val items = resources.getStringArray(R.array.singer)
        aMBinding.amSSingerList.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items)
        aMBinding.amSSingerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2) {
                    0 -> {
                        Log.d("check", "check in " + items[p2])
                    }
                    1 -> {
                        Log.d("check", "check in " + items[p2])
                    }
                    else -> {
                        Log.d("check", "check in else")
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}