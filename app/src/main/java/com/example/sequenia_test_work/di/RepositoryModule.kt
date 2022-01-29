package com.example.sequenia_test_work.di

import com.example.sequenia_test_work.data.Repository
import com.example.sequenia_test_work.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun provideRepository(repository: RepositoryImpl): Repository
}