package com.movie.rick_and_morty.data.responses

import com.movie.rick_and_morty.tools.enums.StatusCharacter
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class CharactersListResponse(
    val info: Info?,
    val results: List<Character> = emptyList()
) {

    @Serializable
    data class Info(
        val count: Int,
        val pages: Int
    )

    @Serializable
    data class Character(
        val id: Int,
        val name: String,
        val status: StatusCharacter,
        val species: String,
        val gender: String,
        val image: String,
        @Transient var isFavorite: Boolean = false
    )
}






