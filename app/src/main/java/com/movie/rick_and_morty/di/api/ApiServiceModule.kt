package com.movie.rick_and_morty.di.api

import com.movie.rick_and_morty.data.ApiService
import com.movie.rick_and_morty.di.annotations.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideApiService(@RetrofitClient retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}