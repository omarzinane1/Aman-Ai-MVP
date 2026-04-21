package com.amanai.app.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AmanLightColorScheme = lightColorScheme(
    primary = AmanDeepBlue,
    onPrimary = Color.White,
    secondary = AmanSafeGreen,
    onSecondary = Color.White,
    error = AmanCriticalRed,
    background = AmanOffWhite,
    onBackground = AmanTextPrimary,
    surface = Color.White,
    onSurface = AmanTextPrimary,
    surfaceVariant = AmanSoftBlue,
    onSurfaceVariant = AmanTextSecondary,
    outline = AmanSoftGray
)

@Composable
fun AmanAiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AmanLightColorScheme,
        typography = AmanTypography,
        shapes = AmanShapes,
        content = content
    )
}
