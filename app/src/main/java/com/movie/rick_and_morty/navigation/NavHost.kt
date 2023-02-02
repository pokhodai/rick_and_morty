package com.movie.rick_and_morty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.movie.rick_and_morty.screens.SplashScreen
import com.movie.rick_and_morty.screens.boarding.BoardingScreen
import com.movie.rick_and_morty.screens.characters.CharactersScreen
import com.movie.rick_and_morty.screens.characters.character.CharacterScreen
import com.movie.rick_and_morty.screens.episodes.EpisodesScreen
import com.movie.rick_and_morty.screens.favorites.FavoritesScreen

enum class Screen(val route: String) {
    SPLASH("splashScreen"),
    BOARDING("boardingScreen"),
    CHARACTER("characterScreen")
}

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.SPLASH.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.SPLASH.route) {
            SplashScreen(navController)
        }

        composable(Screen.BOARDING.route) {
            BoardingScreen {
                navController.navigate(BottomNavItem.Episodes.route) {
                    popUpTo(Screen.BOARDING.route) { inclusive = true }
                }
            }
        }

        composable(BottomNavItem.Characters.route) {
            CharactersScreen(onNavigateToCharacterScreen = { id -> navController.navigate("${Screen.CHARACTER.route}/${id}") })
        }

        composable("${Screen.CHARACTER.route}/{Id}") {
            CharacterScreen(it.arguments?.getString("Id"))
        }

        composable(BottomNavItem.Episodes.route) {
            EpisodesScreen()
        }

        composable(BottomNavItem.Favorites.route) {
            FavoritesScreen()
        }
    }
}

