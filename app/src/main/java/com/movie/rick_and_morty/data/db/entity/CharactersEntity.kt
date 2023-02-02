package com.movie.rick_and_morty.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.movie.rick_and_morty.data.url.DatabaseSettings
import com.movie.rick_and_morty.tools.enums.StatusCharacter

@Entity(tableName = DatabaseSettings.CHARACTERS_TABLE)
data class CharactersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "status") val status: StatusCharacter?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "image") val image: String?,
)
