package com.movie.rick_and_morty.screens.favorites.details.charactes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.movie.rick_and_morty.screens.characters.CharactersCardView
import com.movie.rick_and_morty.ui.theme.LightGrey

@Composable
fun CharactersFavoriteScreen() {

    val viewModel = hiltViewModel<CharactersFavoritesViewModel>()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(LightGrey)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
        ) {
            viewModel.characters?.let {
                items(it) { item ->
                    CharactersCardView(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = Color.White),
                        characterEntity = item,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}