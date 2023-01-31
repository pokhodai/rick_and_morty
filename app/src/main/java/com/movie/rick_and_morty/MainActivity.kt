package com.movie.rick_and_morty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.ui.foundation.Box
import com.movie.rick_and_morty.navigation.BottomNavItem
import com.movie.rick_and_morty.navigation.BottomNavigation
import com.movie.rick_and_morty.navigation.NavigationHost
import com.movie.rick_and_morty.navigation.Screen
import com.movie.rick_and_morty.screens.boarding.BoardingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
private fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        content = {
            NavigationHost(
                modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                navController = navController,
            )
        }
    )
}




