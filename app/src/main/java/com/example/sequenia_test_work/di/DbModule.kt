package com.example.sequenia_test_work.di

import android.content.Context
import androidx.room.Room
import com.example.sequenia_test_work.data.FilmsDao
import com.example.sequenia_test_work.data.FilmsDb
import com.example.sequenia_test_work.data.FilmsDb.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): FilmsDb {
        return Room.databaseBuilder(
            context.applicationContext,
            FilmsDb::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(focusStartDb: FilmsDb): FilmsDao {
        return focusStartDb.filmsDao()
    }
}