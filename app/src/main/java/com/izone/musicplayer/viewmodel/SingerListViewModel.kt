package com.izone.musicplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.izone.musicplayer.model.AlbumList

class SingerListViewModel : ViewModel() {
    //data list를 갖고 있으며, 들어오는 position 값에 따라 list를 바꾸어줌
    //view에서는 position으로 값을 넘기면, 바뀐 list를 받아 recyclerview에 띄워주면 된다.
    private var data_list = MutableLiveData<List<AlbumList>>()

    fun SingerListViewModel(position: Int) {
        //firebase에 연결하여 데이터를 가져와서 처리
    }

    fun getList(): LiveData<List<AlbumList>> {
        return data_list
    }

    fun setList(list: List<AlbumList>){
        data_list.value = list
    }
}