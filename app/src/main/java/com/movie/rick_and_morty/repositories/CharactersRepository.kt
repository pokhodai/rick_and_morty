package com.movie.rick_and_morty.repositories

import com.movie.rick_and_morty.data.ApiService
import com.movie.rick_and_morty.tools.toResultFlow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getCharacters() = toResultFlow {
        apiService.getCharacters(1)
    }
}