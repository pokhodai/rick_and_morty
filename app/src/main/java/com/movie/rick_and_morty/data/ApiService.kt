package com.movie.rick_and_morty.data

import com.movie.rick_and_morty.data.responses.CharactersListResponse
import com.movie.rick_and_morty.data.responses.EpisodesListResponse
import com.movie.rick_and_morty.data.responses.LocationsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiURL.CHARACTERS_URl)
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharactersListResponse>

    @GET(ApiURL.CHARACTERS_URl)
    suspend fun getCharacterById(
        @Query("id") id: Int
    ): Response<CharactersListResponse.Character>

    @GET(ApiURL.EPISODES_URL)
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): Response<EpisodesListResponse>

    @GET(ApiURL.EPISODES_URL)
    suspend fun getEpisodeById(
        @Query("id") id: Int
    ): Response<EpisodesListResponse.Episode>

    @GET(ApiURL.LOCATIONS_URl)
    suspend fun getLocations(
        @Query("page") page: Int
    ): Response<LocationsListResponse>

}