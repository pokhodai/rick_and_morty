package com.movie.rick_and_morty.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class EpisodesListResponse(
    val results: List<Episode> = emptyList()
) {
    @Serializable
    data class Episode(
        val id: String,
        val name: String,
        val episode: String,
        val air_date: String,
    )
}