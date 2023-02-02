package com.movie.rick_and_morty.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.movie.rick_and_morty.data.ApiService
import com.movie.rick_and_morty.data.CharactersDao
import com.movie.rick_and_morty.data.db.entity.CharactersEntity
import com.movie.rick_and_morty.data.sources.CharacterPagingSource
import com.movie.rick_and_morty.tools.toResultFlow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService,
    private val charactersDao: CharactersDao,
) {

    fun getCharacters() = Pager(
        config = PagingConfig(
            pageSize = 15,
        ),
        pagingSourceFactory = {
            CharacterPagingSource(apiService)
        }
    ).flow

    fun getCharactersById(id: String) = toResultFlow {
        apiService.getCharacterById(id.toInt())
    }

    fun insertAll(vararg characters: CharactersEntity) {
        charactersDao.insertAll(*characters)
    }

    fun getCharactersDaoList() = charactersDao.getAll()

    fun getCharactersDaoById(id: Int) = charactersDao.getById(id)

    fun deleteCharactersDaoById(characters: CharactersEntity) =
        charactersDao.deleteCharacters(characters)
}