package com.movie.rick_and_morty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movie.rick_and_morty.screens.SplashScreen
import com.movie.rick_and_morty.screens.boarding.BoardingScreen
import com.movie.rick_and_morty.screens.characters.CharactersScreen
import com.movie.rick_and_morty.screens.episodes.EpisodesScreen
import com.movie.rick_and_morty.screens.locations.LocationsScreen

enum class Screen(val route: String) {
    SPLASH("splashScreen"),
    BOARDING("boardingScreen")
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
            BoardingScreen(navController)
        }

        composable(BottomNavItem.Characters.route) {
            CharactersScreen(onNavigateToCharacterDetailsScreen = { navController.navigate(" ") })
        }

        composable(BottomNavItem.Episodes.route) {
            EpisodesScreen()
        }

        composable(BottomNavItem.Locations.route) {
            LocationsScreen()
        }
    }
}

