package com.movie.rick_and_morty.screens.favorites.details.charactes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.movie.rick_and_morty.data.db.entity.CharactersEntity
import com.movie.rick_and_morty.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersFavoritesViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    var characters: List<CharactersEntity>? by mutableStateOf(charactersRepository.getCharactersDaoList())
        private set

    fun onClickFavorite(characterEntity: CharactersEntity) {
        charactersRepository.deleteCharactersDaoById(characterEntity)
        characters = charactersRepository.getCharactersDaoList()
    }
}