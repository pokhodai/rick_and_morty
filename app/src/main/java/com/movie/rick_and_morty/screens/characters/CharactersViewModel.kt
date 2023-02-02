package com.movie.rick_and_morty.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.movie.rick_and_morty.data.responses.CharactersListResponse
import com.movie.rick_and_morty.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    repository: CharactersRepository,
) : ViewModel() {


    var characters: Flow<PagingData<CharactersListResponse.Character>> =
        repository.getCharacters().cachedIn(viewModelScope)
}