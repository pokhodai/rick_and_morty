package com.movie.rick_and_morty.repositories

import androidx.lifecycle.ViewModel
import com.movie.rick_and_morty.data.ApiService
import com.movie.rick_and_morty.tools.toResultFlow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    fun getCharacters() = toResultFlow {
        apiService.getCharacters(1)
    }
}