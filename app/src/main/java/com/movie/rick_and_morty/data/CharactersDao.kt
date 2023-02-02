package com.movie.rick_and_morty.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.movie.rick_and_morty.data.db.entity.CharactersEntity

@Dao
interface CharactersDao {

    @Insert
    fun insertAll(vararg character: CharactersEntity)

    @Query("SELECT * FROM CHARACTERS_TABLE WHERE id = :id")
    fun getById(id: Int): CharactersEntity?

    @Query("SELECT * FROM CHARACTERS_TABLE")
    fun getAll(): List<CharactersEntity>?

    @Delete
    fun deleteCharacters(character: CharactersEntity)
}