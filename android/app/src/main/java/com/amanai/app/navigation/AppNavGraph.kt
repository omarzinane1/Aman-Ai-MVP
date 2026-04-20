package com.amanai.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amanai.features.fake_weather.WeatherForecastScreen
import com.amanai.features.fake_weather.WeatherHomeScreen
import com.amanai.features.secret_access.SecretAccessPinScreen
import com.amanai.features.splash.SplashScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    startDestination: String = Routes.SECRET_ACCESS_PIN
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
        composable(Routes.WEATHER_HOME) {
            WeatherHomeScreen()
        }
        composable(Routes.WEATHER_FORECAST) {
            WeatherForecastScreen()
        }
        composable(Routes.SECRET_ACCESS_PIN) {
            SecretAccessPinScreen()
        }
    }
}
