package com.amanai.features.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanai.app.theme.AmanAiTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onFinished: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        delay(1200)
        onFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SplashBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-44).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SplashIconCard()
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "AMAN-AI",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 33.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp,
                    lineHeight = 38.sp
                ),
                color = SplashNavy,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "SANCTUARY SKY",
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 4.sp,
                    lineHeight = 12.sp
                ),
                color = SplashMutedText,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SecureStatusPill()
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "WEATHER PROTOCOL V4.2",
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 2.2.sp,
                    lineHeight = 10.sp
                ),
                color = SplashSoftText,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun SplashIconCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.size(width = 128.dp, height = 108.dp),
        shape = RoundedCornerShape(31.dp),
        color = Color.White,
        shadowElevation = 18.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            CloudOutlineIcon(modifier = Modifier.size(width = 70.dp, height = 50.dp))
        }
    }
}

@Composable
private fun CloudOutlineIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val path = Path().apply {
            moveTo(w * 0.22f, h * 0.82f)
            quadraticBezierTo(w * 0.08f, h * 0.82f, w * 0.08f, h * 0.62f)
            quadraticBezierTo(w * 0.08f, h * 0.42f, w * 0.27f, h * 0.42f)
            cubicTo(
                w * 0.31f,
                h * 0.17f,
                w * 0.48f,
                h * 0.03f,
                w * 0.65f,
                h * 0.12f
            )
            cubicTo(
                w * 0.78f,
                h * 0.20f,
                w * 0.84f,
                h * 0.35f,
                w * 0.84f,
                h * 0.55f
            )
            lineTo(w * 0.88f, h * 0.55f)
            quadraticBezierTo(w * 0.98f, h * 0.55f, w * 0.98f, h * 0.70f)
            quadraticBezierTo(w * 0.98f, h * 0.82f, w * 0.86f, h * 0.82f)
            close()
        }

        drawPath(
            path = path,
            color = SplashNavy,
            style = Stroke(
                width = 5.2.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}

@Composable
private fun SecureStatusPill(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(22.dp)
            .background(
                color = SplashPillBackground,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .background(SplashSecureGreen, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "SECURE LINK ACTIVE",
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                lineHeight = 10.sp
            ),
            color = SplashStatusText,
            textAlign = TextAlign.Center
        )
    }
}

private val SplashBackground = Color(0xFFFCFCFD)
private val SplashNavy = Color(0xFF07046D)
private val SplashMutedText = Color(0xFF72717A)
private val SplashSoftText = Color(0xFFA5A4AB)
private val SplashPillBackground = Color(0xFFF0F0F2)
private val SplashSecureGreen = Color(0xFF00A83A)
private val SplashStatusText = Color(0xFF1F2026)

@Preview(name = "Splash Screen", showBackground = true, backgroundColor = 0xFFFCFCFD)
@Composable
private fun SplashScreenPreview() {
    AmanAiTheme {
        SplashScreen()
    }
}
