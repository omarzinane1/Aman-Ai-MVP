package com.amanai.features.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amanai.app.theme.AmanAiTheme
import com.amanai.app.theme.AmanDeepBlue
import com.amanai.app.theme.AmanOffWhite
import com.amanai.app.theme.AmanSoftBlue
import com.amanai.app.theme.AmanSun

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AmanOffWhite)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WeatherLogo()
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "AMAN Weather",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Meteo locale claire, jour apres jour.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .width(156.dp)
                    .height(4.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = AmanSoftBlue
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Chargement des previsions",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun WeatherLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(96.dp)) {
        val corner = 8.dp.toPx()
        val iconSize = size.minDimension

        drawRoundRect(
            color = AmanDeepBlue,
            size = Size(iconSize, iconSize),
            cornerRadius = CornerRadius(corner, corner)
        )
        drawCircle(
            color = AmanSun,
            radius = iconSize * 0.18f,
            center = Offset(iconSize * 0.67f, iconSize * 0.34f)
        )
        drawCircle(
            color = Color.White,
            radius = iconSize * 0.18f,
            center = Offset(iconSize * 0.37f, iconSize * 0.57f)
        )
        drawCircle(
            color = Color.White,
            radius = iconSize * 0.23f,
            center = Offset(iconSize * 0.53f, iconSize * 0.51f)
        )
        drawRoundRect(
            color = Color.White,
            topLeft = Offset(iconSize * 0.23f, iconSize * 0.55f),
            size = Size(iconSize * 0.56f, iconSize * 0.18f),
            cornerRadius = CornerRadius(corner, corner)
        )
    }
}

@Preview(name = "Splash Screen", showBackground = true, backgroundColor = 0xFFF8FAF7)
@Composable
private fun SplashScreenPreview() {
    AmanAiTheme {
        SplashScreen()
    }
}
