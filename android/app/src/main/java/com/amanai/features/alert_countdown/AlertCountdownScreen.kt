package com.amanai.features.alert_countdown

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
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
fun AlertCountdownScreen(
    modifier: Modifier = Modifier,
    onCancelRequest: () -> Unit = {},
    onCountdownFinished: () -> Unit = {}
) {
    var secondsRemaining by remember { mutableIntStateOf(24) }

    LaunchedEffect(secondsRemaining) {
        if (secondsRemaining > 0) {
            delay(1000)
            secondsRemaining -= 1
        } else {
            onCountdownFinished()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AlertCountdownBackground,
                        AlertCountdownBottom
                    )
                )
            )
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopEmergencyMarker()
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "Emergency Alert",
                style = CountdownTitleStyle,
                color = CountdownText
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Requesting help in AMAN-AI network",
                style = CountdownBodyStyle.copy(
                    fontSize = 9.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = CountdownBody,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(22.dp))
            CountdownCircle(secondsRemaining = secondsRemaining)
            Spacer(modifier = Modifier.height(24.dp))
            SendingCard(secondsRemaining = secondsRemaining)
            Spacer(modifier = Modifier.height(32.dp))
            CancelRequestButton(onClick = onCancelRequest)
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "False alarm? Tap cancel to abort the emergency transmission\nimmediately.",
                style = CountdownBodyStyle.copy(
                    fontSize = 7.sp,
                    lineHeight = 10.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = CountdownHint,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun TopEmergencyMarker() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(18.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(CountdownRed, RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp))
        )
        Surface(
            modifier = Modifier.size(20.dp),
            shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp, topStart = 0.dp, topEnd = 0.dp),
            color = CountdownRed,
            shadowElevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                ShieldTopIcon(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
private fun CountdownCircle(secondsRemaining: Int) {
    Box(
        modifier = Modifier.size(192.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(196.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            CountdownGlow,
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )
        Surface(
            modifier = Modifier.size(164.dp),
            shape = CircleShape,
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(3.dp, CountdownRing),
            shadowElevation = 0.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = secondsRemaining.toString(),
                    style = CountdownDigitsStyle,
                    color = CountdownRed
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "SECONDS",
                    style = CountdownCapsStyle.copy(
                        fontSize = 11.sp,
                        lineHeight = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.6.sp
                    ),
                    color = CountdownRed
                )
            }
        }
    }
}

@Composable
private fun SendingCard(secondsRemaining: Int) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = CountdownCard,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .background(CountdownRed, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsteriskIcon(modifier = Modifier.size(12.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Sending in ${secondsRemaining}s",
                    style = CountdownBodyStyle.copy(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = CountdownText
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "GPS and Health data will be shared",
                    style = CountdownBodyStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = CountdownBody
                )
            }
        }
    }
}

@Composable
private fun CancelRequestButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        color = CountdownButton,
        shadowElevation = 0.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "Cancel Request",
                style = CountdownBodyStyle.copy(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = CountdownText
            )
        }
    }
}

@Composable
private fun ShieldTopIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.50f, size.height * 0.08f)
            lineTo(size.width * 0.78f, size.height * 0.20f)
            lineTo(size.width * 0.72f, size.height * 0.56f)
            quadraticBezierTo(size.width * 0.66f, size.height * 0.78f, size.width * 0.50f, size.height * 0.92f)
            quadraticBezierTo(size.width * 0.34f, size.height * 0.78f, size.width * 0.28f, size.height * 0.56f)
            lineTo(size.width * 0.22f, size.height * 0.20f)
            close()
        }
        drawPath(path = path, color = Color.White)
    }
}

@Composable
private fun AsteriskIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = 1.6.dp.toPx()
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.50f, size.height * 0.16f),
            end = Offset(size.width * 0.50f, size.height * 0.84f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.16f, size.height * 0.50f),
            end = Offset(size.width * 0.84f, size.height * 0.50f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.24f, size.height * 0.24f),
            end = Offset(size.width * 0.76f, size.height * 0.76f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.76f, size.height * 0.24f),
            end = Offset(size.width * 0.24f, size.height * 0.76f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

private val AlertCountdownBackground = Color(0xFFFDFBFB)
private val AlertCountdownBottom = Color(0xFFF9F6F6)
private val CountdownRed = Color(0xFFD12222)
private val CountdownGlow = Color(0xFFF2C6C6)
private val CountdownRing = Color(0xFFF0C9C9)
private val CountdownCard = Color(0xFFE8E9ED)
private val CountdownButton = Color(0xFFDDE0E5)
private val CountdownText = Color(0xFF212325)
private val CountdownBody = Color(0xFF7B7E88)
private val CountdownHint = Color(0xFFB1B4BF)

private val CountdownTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 18.sp,
    lineHeight = 22.sp,
    fontWeight = FontWeight.ExtraBold
)

private val CountdownDigitsStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 44.sp,
    lineHeight = 46.sp,
    fontWeight = FontWeight.ExtraBold
)

private val CountdownBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val CountdownCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 8.sp,
    lineHeight = 10.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 1.0.sp
)

@Preview(name = "Alert Countdown", showBackground = true, backgroundColor = 0xFFFDFBFB)
@Composable
private fun AlertCountdownScreenPreview() {
    AmanAiTheme {
        AlertCountdownScreen()
    }
}
