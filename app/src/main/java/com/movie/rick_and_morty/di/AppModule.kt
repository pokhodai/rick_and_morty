package com.movie.rick_and_morty.di

import android.content.Context
import com.movie.rick_and_morty.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApp(@ApplicationContext context: Context): App = context.applicationContext as App
}