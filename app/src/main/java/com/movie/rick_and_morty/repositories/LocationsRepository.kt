package com.movie.rick_and_morty.repositories

import com.movie.rick_and_morty.data.ApiService
import com.movie.rick_and_morty.tools.toResultFlow
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getLocations() = toResultFlow {
        apiService.getLocations(1)
    }

    fun getLocationById(id: Int) = toResultFlow {
        apiService.getLocationById(id)
    }
}