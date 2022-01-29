package com.example.sequenia_test_work.di

import com.example.sequenia_test_work.api.FilmsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class ApiModule {

    private companion object {
        const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): FilmsApi {
        return retrofit.create(FilmsApi::class.java)
    }
}