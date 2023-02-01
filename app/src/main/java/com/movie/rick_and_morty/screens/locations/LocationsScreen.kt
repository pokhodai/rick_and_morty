package com.movie.rick_and_morty.screens.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.movie.rick_and_morty.ui.theme.LightGrey

@Composable
fun LocationsScreen() {

    val systemUiController = rememberSystemUiController()

    val viewModel = hiltViewModel<LocationsViewModel>()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = LightGrey,
            darkIcons = true
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(LightGrey)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
        ) {

        }
    }
}