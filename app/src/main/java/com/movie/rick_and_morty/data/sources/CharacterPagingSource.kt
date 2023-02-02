package com.movie.rick_and_morty.data.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movie.rick_and_morty.data.ApiService
import com.movie.rick_and_morty.data.responses.CharactersListResponse
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val apiService: ApiService,
) : PagingSource<Int, CharactersListResponse.Character>() {

    override fun getRefreshKey(state: PagingState<Int, CharactersListResponse.Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersListResponse.Character> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getCharacters(page = page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}