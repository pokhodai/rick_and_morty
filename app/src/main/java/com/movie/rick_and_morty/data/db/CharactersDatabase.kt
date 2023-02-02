package com.movie.rick_and_morty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movie.rick_and_morty.data.CharactersDao
import com.movie.rick_and_morty.data.db.entity.CharactersEntity

@Database(entities = [CharactersEntity::class], version = 2)
abstract class CharactersDatabase() : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}