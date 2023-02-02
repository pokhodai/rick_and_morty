package com.movie.rick_and_morty.screens.episodes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.rick_and_morty.data.responses.EpisodesListResponse
import com.movie.rick_and_morty.repositories.EpisodesRepository
import com.movie.rick_and_morty.tools.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    var episodes by mutableStateOf(listOf<EpisodesListResponse.Episode>())
        private set

    fun onSwipeRefresh() {
        viewModelScope.launch(Dispatchers.Default) {
            episodesRepository.getEpisodes().collectLatest { result ->
                when (result) {
                    is ApiResult.Loading -> _isRefreshing.value = true
                    is ApiResult.Success -> {
                        result.data?.results?.let {
                            episodes = it
                        }
                        _isRefreshing.value = false
                    }
                    is ApiResult.Error -> {

                    }
                    else -> Unit
                }
            }
        }
    }

    init {
        onSwipeRefresh()
    }


}