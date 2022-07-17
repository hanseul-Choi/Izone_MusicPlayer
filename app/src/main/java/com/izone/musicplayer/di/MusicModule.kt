package com.izone.musicplayer.di

import com.izone.musicplayer.repository.music.MusicControlDao
import com.izone.musicplayer.repository.music.MusicControlDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MusicModule {

    @Provides
    fun provideMusicDao() : MusicControlDao {
        return MusicControlDaoImpl()
    }
}