package com.movie.rick_and_morty.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.ui.theme.Green
import com.movie.rick_and_morty.ui.theme.LightGreen
import com.movie.rick_and_morty.ui.theme.LightGrey2

sealed class BottomNavItem(val title: Int, val icon: Int, val route: String) {
    object Episodes :
        BottomNavItem(R.string.bottom_nav_episodes, R.drawable.ic_episode, "episodesScreen")

    object Characters :
        BottomNavItem(R.string.bottom_nav_characters, R.drawable.ic_character, "charactersScreen")

    object Locations :
        BottomNavItem(R.string.bottom_nav_locations, R.drawable.ic_location, "locationsScreen")
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Episodes,
        BottomNavItem.Characters,
        BottomNavItem.Locations
    )

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    when (currentRoute?.route) {
        Screen.SPLASH.route -> {
            bottomBarState.value = false
        }
        Screen.BOARDING.route -> {
            bottomBarState.value = false
        }
        BottomNavItem.Characters.route -> {
            bottomBarState.value = true
        }
        BottomNavItem.Episodes.route -> {
            bottomBarState.value = true
        }
        BottomNavItem.Locations.route -> {
            bottomBarState.value = true
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