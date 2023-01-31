package com.movie.rick_and_morty.tools.enums

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

enum class StatusCharacter(val text: String) {
    @SerializedName("Alive") ALIVE("Alive"),
    @SerializedName("Dead") DEAD("Dead"),
    @SerializedName("unknown") UNKNOWN("Unknown")
}