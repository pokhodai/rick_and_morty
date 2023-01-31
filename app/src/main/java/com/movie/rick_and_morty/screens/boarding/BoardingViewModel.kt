package com.movie.rick_and_morty.screens.boarding

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.rick_and_morty.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BoardingViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    var nameState by mutableStateOf("")
        private set

    var enabledState by mutableStateOf(false)
        private set

    val titleState: String by mutableStateOf((context.resources.getStringArray(R.array.boarding_citation).toList().random()))

    init {
        snapshotFlow { nameState }
            .mapLatest { search ->
                search.trim().isNotEmpty()
            }
            .onEach {
                enabledState = it
            }
            .launchIn(viewModelScope)
    }

    fun onChangeName(it: String) {
        nameState = it
    }
}