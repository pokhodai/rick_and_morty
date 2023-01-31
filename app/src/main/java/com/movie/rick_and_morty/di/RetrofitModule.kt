package com.movie.rick_and_morty.di

import com.google.gson.GsonBuilder
import com.movie.rick_and_morty.data.ApiURL
import com.movie.rick_and_morty.di.annotations.HttpClient
import com.movie.rick_and_morty.di.annotations.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().setLenient().create()
    )

    @Provides
    @Singleton
    @RetrofitClient
    fun retrofitApi(
        @HttpClient okHttpClient: OkHttpClient,
        factory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiURL.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(factory)
            .build()
}