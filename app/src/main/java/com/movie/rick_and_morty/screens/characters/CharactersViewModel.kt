package com.movie.rick_and_morty.screens.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.rick_and_morty.data.responses.CharactersListResponse
import com.movie.rick_and_morty.repositories.CharactersRepository
import com.movie.rick_and_morty.tools.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    var characters by mutableStateOf(listOf<CharactersListResponse.Character>())
        private set

    init {
        onSwipeRefresh()
    }

    fun onSwipeRefresh() {
        viewModelScope.launch(Dispatchers.Default) {
            charactersRepository.getCharacters().collectLatest {
                when (it) {
                    is ApiResult.Loading -> _isRefreshing.update { true }
                    is ApiResult.Success -> {
                        it.data?.results?.let { list ->
                            characters = list
                        }
                        _isRefreshing.update { false }
                    }
                    is ApiResult.Error -> {

                    }
                    else -> Unit
                }
            }
        }
    }
}