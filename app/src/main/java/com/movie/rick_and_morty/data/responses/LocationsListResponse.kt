package com.movie.rick_and_morty.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class LocationsListResponse(
    val result: List<Location> = emptyList()
) {
    @Serializable
    data class Location(
        val id: String,
        val name: String,
        val type: String,
        val dimension: String
    )
}