package com.movie.rick_and_morty.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.movie.rick_and_morty.R
import com.movie.rick_and_morty.navigation.Screen
import com.movie.rick_and_morty.ui.theme.LightGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = LightGreen,
            darkIcons = false
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreen),
        contentAlignment = Alignment.Center
    ) {
        val scale = remember {
            androidx.compose.animation.core.Animatable(0.0f)
        }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(800, easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
            )
            delay(1000)
            navController.navigate(Screen.BOARDING.route) {
                popUpTo(Screen.SPLASH.route) {
                    inclusive = true
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.plumbus),
            contentDescription = "Splash.Image.Plumbus",
            modifier = Modifier
                .size(300.dp).padding(40.dp)
                .scale(scale.value)
        )
    }
}