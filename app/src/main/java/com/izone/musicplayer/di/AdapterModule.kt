package com.izone.musicplayer.di

import com.izone.musicplayer.recyclerview.MusicRepositoryAdapter
import com.izone.musicplayer.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

//    @Singleton
//    @Provides
//    fun provideMusicAdapter(viewModel: MainViewModel) : MusicRepositoryAdapter {
//        return MusicRepositoryAdapter(viewModel)
//    }
}