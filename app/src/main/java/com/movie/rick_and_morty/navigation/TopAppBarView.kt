package com.movie.rick_and_morty.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.ui.theme.LightGrey
import com.movie.rick_and_morty.ui.theme.asaRussFontFamily

@Composable
fun TopAppBarView(navController: NavController) {

    val topAppBarState = rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    val isVisibleIcon = rememberSaveable { mutableStateOf(true) }

    when (currentRoute?.route) {
        Screen.SPLASH.route, Screen.BOARDING.route -> {
            topAppBarState.value = false
        }
        BottomNavItem.Characters.route, BottomNavItem.Episodes.route, BottomNavItem.Favorites.route -> {
            topAppBarState.value = true
            isVisibleIcon.value = false
        }
    }

    AnimatedVisibility(
        visible = topAppBarState.value,
        content = {
            TopAppBar(
                title = {
                    Row() {
                        if (isVisibleIcon.value) {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_back),
                                    contentDescription = "Icon.TopBar"
                                )
                            }
                        }
                    }
                    when (currentRoute?.route) {
                        BottomNavItem.Characters.route -> {
                            TitleMainTopAppBar(R.string.characters_screen_title)
                        }
                        BottomNavItem.Episodes.route -> {
                            TitleMainTopAppBar(R.string.episode_title)
                        }
                        BottomNavItem.Favorites.route -> {
                            TitleMainTopAppBar(R.string.favorites_title)
                        }
                        Screen.CHARACTER.route + "/{Id}" -> {
                            TitleCharacterTopAppBar()
                            isVisibleIcon.value = true
                        }
                    }
                },
                backgroundColor = LightGrey,
                elevation = 0.dp,
            )
        }
    )
}

@Composable
private fun TitleMainTopAppBar(title: Int) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = title),
        style = TextStyle(
            fontSize = 32.sp,
            fontFamily = asaRussFontFamily
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun TitleCharacterTopAppBar() {
    Text(
        text = "Character",
        style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif, color = Color.Black)
    )
}