package com.movie.rick_and_morty.data.responses

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class LocationsListResponse(
    val results: List<Location> = emptyList()
) {
    @Serializable
    data class Location(
        val id: String,
        val name: String,
        val type: String,
        val dimension: String,
        @Transient var check: Boolean = false
    )
}