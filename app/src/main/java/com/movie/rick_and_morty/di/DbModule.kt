package com.movie.rick_and_morty.di

import android.content.Context
import androidx.room.Room
import com.movie.rick_and_morty.data.db.CharactersDatabase
import com.movie.rick_and_morty.data.url.DatabaseSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, CharactersDatabase::class.java, DatabaseSettings.CHARACTERS_DATABASE
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: CharactersDatabase) = db.charactersDao()
}