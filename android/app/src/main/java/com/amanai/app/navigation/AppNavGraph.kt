package com.amanai.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amanai.features.alert_cancelled.AlertCancelledScreen
import com.amanai.features.alert_history.AlertHistoryScreen
import com.amanai.features.alert_delay.AlertDelayConfigScreen
import com.amanai.features.alert_countdown.AlertCountdownScreen
import com.amanai.features.alert_detected.AlertDetectedScreen
import com.amanai.features.alert_sent_status.AlertSentStatusScreen
import com.amanai.features.detection_status.DetectionStatusScreen
import com.amanai.features.fake_weather.WeatherForecastScreen
import com.amanai.features.fake_weather.WeatherHomeScreen
import com.amanai.features.onboarding.OnboardingScreen
import com.amanai.features.panic_quick_erase.PanicQuickEraseScreen
import com.amanai.features.permissions.PermissionsSetupScreen
import com.amanai.features.safety_dashboard.SafetyDashboardScreen
import com.amanai.features.safety_settings.SafetySettingsScreen
import com.amanai.features.smartwatch_sync.SmartwatchSyncScreen
import com.amanai.features.secret_access.SecretAccessPinScreen
import com.amanai.features.splash.SplashScreen
import com.amanai.features.trust_contacts.TrustContactsScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    startDestination: String = Routes.SPLASH
) {
    val navController = rememberNavController()
    fun navigateSingleTop(route: String) {
        if (navController.currentDestination?.route == route) return
        navController.navigate(route) {
            launchSingleTop = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Routes.WEATHER_FORECAST) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Routes.WEATHER_HOME) {
            WeatherHomeScreen(
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SECRET_ACCESS_PIN) },
                onSettingsClick = { navigateSingleTop(Routes.SECRET_ACCESS_PIN) }
            )
        }
        composable(Routes.WEATHER_FORECAST) {
            WeatherForecastScreen(
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SECRET_ACCESS_PIN) },
                onSettingsClick = { navigateSingleTop(Routes.SECRET_ACCESS_PIN) }
            )
        }
        composable(Routes.SECRET_ACCESS_PIN) {
            SecretAccessPinScreen(
                onPinValidated = { navigateSingleTop(Routes.ONBOARDING) }
            )
        }
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onContinue = { navigateSingleTop(Routes.PERMISSIONS_SETUP) }
            )
        }
        composable(Routes.PERMISSIONS_SETUP) {
            PermissionsSetupScreen(
                onCompleteSetup = { navigateSingleTop(Routes.TRUST_CONTACTS) },
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.PERMISSIONS_SETUP) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.TRUST_CONTACTS) {
            TrustContactsScreen(
                onSaveContinue = { navigateSingleTop(Routes.ALERT_DELAY_CONFIG) },
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.TRUST_CONTACTS) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.ALERT_DELAY_CONFIG) {
            AlertDelayConfigScreen(
                onSaveSettings = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onSettingsClick = { navigateSingleTop(Routes.ALERT_DELAY_CONFIG) }
            )
        }
        composable(Routes.SAFETY_DASHBOARD) {
            SafetyDashboardScreen(
                onSosClick = { navigateSingleTop(Routes.ALERT_DETECTED) },
                onHistoryClick = { navigateSingleTop(Routes.ALERT_HISTORY) },
                onAdvancedSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) },
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.DETECTION_STATUS) {
            DetectionStatusScreen(
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.DETECTION_STATUS) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.ALERT_DETECTED) {
            AlertDetectedScreen(
                onConfirmSos = { navigateSingleTop(Routes.ALERT_COUNTDOWN) },
                onCancelAlert = { navigateSingleTop(Routes.ALERT_CANCELLED) }
            )
        }
        composable(Routes.ALERT_COUNTDOWN) {
            AlertCountdownScreen(
                onCancelRequest = { navigateSingleTop(Routes.ALERT_CANCELLED) },
                onCountdownFinished = { navigateSingleTop(Routes.ALERT_SENT_STATUS) }
            )
        }
        composable(Routes.ALERT_CANCELLED) {
            AlertCancelledScreen(
                onReturnToDashboard = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.ALERT_SENT_STATUS) {
            AlertSentStatusScreen(
                onEndAlert = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.ALERT_HISTORY) {
            AlertHistoryScreen(
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.SAFETY_SETTINGS) {
            SafetySettingsScreen(
                onEmergencyContactsClick = { navigateSingleTop(Routes.TRUST_CONTACTS) },
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.PANIC_QUICK_ERASE) {
            PanicQuickEraseScreen(
                onClose = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
        composable(Routes.SMARTWATCH_SYNC) {
            SmartwatchSyncScreen(
                onWeatherClick = { navigateSingleTop(Routes.WEATHER_HOME) },
                onForecastClick = { navigateSingleTop(Routes.WEATHER_FORECAST) },
                onSafetyClick = { navigateSingleTop(Routes.SAFETY_DASHBOARD) },
                onSettingsClick = { navigateSingleTop(Routes.SAFETY_SETTINGS) }
            )
        }
    }
}
