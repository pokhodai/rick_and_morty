package com.movie.rick_and_morty.data.responses

import com.google.gson.annotations.SerializedName
import com.movie.rick_and_morty.tools.enums.StatusCharacter
import kotlinx.serialization.Serializable

@Serializable
data class CharactersListResponse(
    val results: List<Character>
) {

    @Serializable
    data class Character(
        val name: String,
        val status: StatusCharacter,
        val species: String,
        val gender: String,
        val image: String
    )
}






