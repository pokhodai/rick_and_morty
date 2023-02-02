package com.movie.rick_and_morty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.movie.rick_and_morty.navigation.BottomNavigation
import com.movie.rick_and_morty.navigation.NavigationHost
import com.movie.rick_and_morty.navigation.TopAppBarView
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
        topBar = { TopAppBarView(navController = navController) },
        bottomBar = { BottomNavigation(navController = navController) },
        content = {
            NavigationHost(
                modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                navController = navController,
            )
        }
    )
}




