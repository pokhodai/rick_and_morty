package com.movie.rick_and_morty.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.ui.theme.Green
import com.movie.rick_and_morty.ui.theme.LightGreen
import com.movie.rick_and_morty.ui.theme.LightGrey
import com.movie.rick_and_morty.ui.theme.LightGrey2

sealed class BottomNavItem(val title: Int, val icon: Int, val route: String) {
    object Episodes :
        BottomNavItem(R.string.bottom_nav_episodes, R.drawable.ic_episode, "episodesScreen")

    object Characters :
        BottomNavItem(R.string.bottom_nav_characters, R.drawable.ic_character, "charactersScreen")

    object Favorites :
        BottomNavItem(R.string.bottom_nav_favorites, R.drawable.ic_favorites, "favoritesScreen")
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Episodes,
        BottomNavItem.Characters,
        BottomNavItem.Favorites
    )

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    val systemUiController = rememberSystemUiController()

    SideEffect {
        when (currentRoute?.route) {
            Screen.SPLASH.route, Screen.BOARDING.route -> {
                systemUiController.setStatusBarColor(
                    color = LightGreen,
                    darkIcons = false
                )
                bottomBarState.value = false
            }
            Screen.CHARACTER.route + "/{Id}",
            Screen.CHARACTERS_FAVORITE.route -> {
                bottomBarState.value = false
                systemUiController.setStatusBarColor(
                    color = LightGrey,
                    darkIcons = true
                )
            }
            BottomNavItem.Characters.route,
            BottomNavItem.Episodes.route,
            BottomNavItem.Favorites.route -> {
                bottomBarState.value = true
                systemUiController.setStatusBarColor(
                    color = LightGrey,
                    darkIcons = true
                )
            }
        }
    }

    AnimatedVisibility(
        visible = bottomBarState.value,
        content = {
            BottomNavigation(
                backgroundColor = LightGrey2,
                contentColor = LightGrey2
            ) {
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute?.route == item.route,
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = stringResource(id = item.title)
                            )
                        },
                        label = { Text(text = stringResource(id = item.title), fontSize = 9.sp) },
                        selectedContentColor = Green,
                        unselectedContentColor = LightGreen,
                        alwaysShowLabel = true,
                        onClick = {
                            if (item.route != currentRoute?.route) {
                                navController.navigate(item.route) {
                                    if (currentRoute != null) {
                                        popUpTo(currentRoute.id) {
                                            saveState = true
                                            inclusive = true
                                        }
                                    } else {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                            inclusive = true
                                        }
                                    }

                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}