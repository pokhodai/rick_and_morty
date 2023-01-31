package com.movie.rick_and_morty.data

import com.movie.rick_and_morty.data.responses.CharactersListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiURL.CHARACTERS_URl)
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharactersListResponse>

    suspend fun getCharacterById(
        @Query("id") id: Int
    )
}