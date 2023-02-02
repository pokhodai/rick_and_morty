package com.movie.rick_and_morty.screens.characters.character

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.rick_and_morty.data.db.entity.CharactersEntity
import com.movie.rick_and_morty.data.responses.CharactersListResponse
import com.movie.rick_and_morty.repositories.CharactersRepository
import com.movie.rick_and_morty.tools.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val characterId: String = checkNotNull(savedStateHandle["Id"])
    var character: MutableState<CharactersListResponse.Character?> = mutableStateOf(null)
        private set

    var isFavorite by mutableStateOf(false)
        private set

    init {
        getCharacterById(characterId)
        checkFavorite()
    }

    private fun checkFavorite() {
        isFavorite = charactersRepository.getCharactersDaoById(characterId.toInt()) != null
    }

    private fun getCharacterById(characterId: String) {
        viewModelScope.launch(Dispatchers.Default) {
            charactersRepository.getCharactersById(characterId).collect { result ->
                when (result) {
                    is ApiResult.Loading -> {

                    }
                    is ApiResult.Success -> {
                        result.data?.let {
                            character.value = it

                        }
                    }
                    is ApiResult.Error -> {

                    }
                    else -> Unit
                }
            }
        }
    }

    fun onClickFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val characterEntity = CharactersEntity(
                id = characterId.toInt(),
                name = character.value?.name,
                gender = character.value?.gender,
                species = character.value?.species,
                status = character.value?.status,
                image = character.value?.image
            )

            if (isFavorite) {
                character.value?.let {
                    charactersRepository.deleteCharactersDaoById(characters = characterEntity)
                }
            } else {
                charactersRepository.insertAll(characterEntity)
            }
            checkFavorite()
        }
    }
}