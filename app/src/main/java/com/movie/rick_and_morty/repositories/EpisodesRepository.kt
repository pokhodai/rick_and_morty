package com.movie.rick_and_morty.repositories

import com.movie.rick_and_morty.data.ApiService
import com.movie.rick_and_morty.tools.toResultFlow
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getEpisodes() = toResultFlow {
        apiService.getEpisodes(1)
    }

    fun getEpisodeById(id: Int) = toResultFlow {
        apiService.getEpisodeById(id)
    }
}