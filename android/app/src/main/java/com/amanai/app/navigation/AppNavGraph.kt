package com.amanai.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amanai.features.splash.SplashScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    startDestination: String = Routes.SPLASH
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Routes.SPLASH) {
            SplashScreen()
        }
    }
}
