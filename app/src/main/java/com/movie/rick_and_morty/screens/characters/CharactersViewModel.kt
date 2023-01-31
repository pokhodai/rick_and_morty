package com.movie.rick_and_morty.screens.characters

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.rick_and_morty.data.responses.CharactersListResponse
import com.movie.rick_and_morty.repositories.CharactersRepository
import com.movie.rick_and_morty.tools.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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
                    else -> {}
                }
            }
        }
    }

    init {
        onSwipeRefresh()
//        snapshotFlow { refresh }
//            .mapLatest { isRefresh ->
//                if (isRefresh) getCharacters()
//            }
//            .onEach {
//                refresh = false
//            }
//            .launchIn(viewModelScope)

    }
}