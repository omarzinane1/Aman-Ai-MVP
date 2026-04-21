package com.amanai.features.alert_cancelled

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanai.app.theme.AmanAiTheme

@Composable
fun AlertCancelledScreen(
    modifier: Modifier = Modifier,
    onReturnToDashboard: () -> Unit = {},
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AlertCancelledBackgroundTop,
                        AlertCancelledBackgroundBottom
                    )
                )
            )
    ) {
        AlertCancelledHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 72.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp)
                .padding(bottom = 92.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            ConfirmationHero()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Alert Cancelled",
                style = CancelledTitleStyle,
                color = CancelledNavy,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = buildAnnotatedString {
                    append("Your status has been updated. You are\nmarked as ")
                    pushStyle(SpanStyle(color = CancelledGreenText, fontWeight = FontWeight.Bold))
                    append("Safe")
                    pop()
                    append(".")
                },
                style = CancelledBodyStyle.copy(
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = CancelledBody,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            InfoCard(
                icon = CancelInfoIcon.Shield,
                iconContainer = CancelledSoftBlue,
                iconTint = CancelledNavy,
                title = "Response Standing Down",
                subtitle = "Emergency contacts and local services have\nbeen notified that no further assistance is\nrequired.",
                background = CancelledCardMuted
            )
            Spacer(modifier = Modifier.height(12.dp))
            InfoCard(
                icon = CancelInfoIcon.Check,
                iconContainer = CancelledSoftGreen,
                iconTint = CancelledGreenText,
                title = "Identity Verified",
                subtitle = "Cancellation request confirmed via your\nprimary authentication method.",
                background = Color.White
            )
            Spacer(modifier = Modifier.height(22.dp))
            ReturnButton(onClick = onReturnToDashboard)
            Spacer(modifier = Modifier.height(12.dp))
        }

        CancelledBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun AlertCancelledHeader(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CloudLogo(modifier = Modifier.size(width = 15.dp, height = 10.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "AMAN-AI",
                style = CancelledBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = CancelledNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            UserAvatar()
        }
    }
}

@Composable
private fun ConfirmationHero() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            CancelledGlow,
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .size(68.dp)
                .background(CancelledGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            CheckIcon(
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun InfoCard(
    icon: CancelInfoIcon,
    iconContainer: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    background: Color
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = background,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(iconContainer, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                WhenIcon(
                    type = icon,
                    modifier = Modifier.size(12.dp),
                    tint = iconTint
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = title,
                    style = CancelledBodyStyle.copy(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = CancelledText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = CancelledBodyStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = CancelledBody
                )
            }
        }
    }
}

@Composable
private fun ReturnButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(21.dp),
        color = CancelledNavy,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Return to Dashboard",
                style = CancelledBodyStyle.copy(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "->",
                style = CancelledBodyStyle.copy(
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
    }
}

@Composable
private fun CancelledBottomNav(
    modifier: Modifier = Modifier,
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(63.dp)
            .navigationBarsPadding(),
        color = Color.White,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            BottomNavItem(type = BottomNavType.Weather, label = "Weather", selected = false, onClick = onWeatherClick)
            BottomNavItem(type = BottomNavType.Forecast, label = "Forecast", selected = false, onClick = onForecastClick)
            BottomNavItem(type = BottomNavType.Safety, label = "Safety", selected = true, onClick = onSafetyClick)
            BottomNavItem(type = BottomNavType.Settings, label = "Settings", selected = false, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun BottomNavItem(type: BottomNavType, label: String, selected: Boolean, onClick: () -> Unit) {
    val tint = if (selected) CancelledNavy else CancelledNavMuted
    Column(
        modifier = Modifier
            .widthIn(min = 40.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(29.dp)
                    .background(CancelledSelectedPill, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                BottomNavIcon(
                    type = type,
                    modifier = Modifier.size(15.dp),
                    tint = tint
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
        } else {
            Spacer(modifier = Modifier.height(5.dp))
            BottomNavIcon(
                type = type,
                modifier = Modifier.size(15.dp),
                tint = tint
            )
            Spacer(modifier = Modifier.height(7.dp))
        }
        Text(
            text = label,
            style = CancelledBodyStyle.copy(
                fontSize = 7.sp,
                lineHeight = 9.sp,
                fontWeight = FontWeight.Medium
            ),
            color = tint,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun UserAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(22.dp)) {
        drawCircle(color = CancelledAvatarBg)
        drawCircle(
            color = CancelledAvatarFace,
            radius = size.minDimension * 0.17f,
            center = Offset(size.width * 0.50f, size.height * 0.35f)
        )
        drawOval(
            color = CancelledAvatarFace,
            topLeft = Offset(size.width * 0.32f, size.height * 0.52f),
            size = Size(size.width * 0.36f, size.height * 0.20f)
        )
        drawArc(
            color = CancelledAvatarHair,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width * 0.26f, size.height * 0.10f),
            size = Size(size.width * 0.48f, size.height * 0.32f)
        )
        drawRect(
            color = CancelledAvatarAccent,
            topLeft = Offset(size.width * 0.60f, size.height * 0.52f),
            size = Size(size.width * 0.10f, size.height * 0.24f)
        )
    }
}

@Composable
private fun CloudLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        val path = Path().apply {
            moveTo(size.width * 0.10f, size.height * 0.75f)
            quadraticBezierTo(size.width * 0.02f, size.height * 0.54f, size.width * 0.27f, size.height * 0.50f)
            quadraticBezierTo(size.width * 0.38f, size.height * 0.13f, size.width * 0.63f, size.height * 0.32f)
            quadraticBezierTo(size.width * 0.80f, size.height * 0.36f, size.width * 0.82f, size.height * 0.62f)
            quadraticBezierTo(size.width * 0.96f, size.height * 0.65f, size.width * 0.91f, size.height * 0.79f)
            close()
        }
        drawPath(path = path, color = CancelledNavy, style = stroke)
    }
}

@Composable
private fun CheckIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.White
) {
    Canvas(modifier = modifier) {
        drawLine(
            color = tint,
            start = Offset(size.width * 0.22f, size.height * 0.54f),
            end = Offset(size.width * 0.42f, size.height * 0.74f),
            strokeWidth = 2.2.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.42f, size.height * 0.74f),
            end = Offset(size.width * 0.78f, size.height * 0.30f),
            strokeWidth = 2.2.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun WhenIcon(
    type: CancelInfoIcon,
    modifier: Modifier = Modifier,
    tint: Color = CancelledNavy
) {
    Canvas(modifier = modifier) {
        when (type) {
            CancelInfoIcon.Shield -> {
                val shield = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.08f)
                    lineTo(size.width * 0.78f, size.height * 0.20f)
                    lineTo(size.width * 0.72f, size.height * 0.56f)
                    quadraticBezierTo(size.width * 0.66f, size.height * 0.78f, size.width * 0.50f, size.height * 0.92f)
                    quadraticBezierTo(size.width * 0.34f, size.height * 0.78f, size.width * 0.28f, size.height * 0.56f)
                    lineTo(size.width * 0.22f, size.height * 0.20f)
                    close()
                }
                drawPath(path = shield, color = tint)
                drawCircle(
                    color = Color.White,
                    radius = size.minDimension * 0.10f,
                    center = Offset(size.width * 0.50f, size.height * 0.42f)
                )
            }
            CancelInfoIcon.Check -> {
                drawCircle(
                    color = tint,
                    radius = size.minDimension * 0.42f,
                    center = Offset(size.width / 2f, size.height / 2f),
                    style = Stroke(width = 1.2.dp.toPx())
                )
                drawLine(
                    color = tint,
                    start = Offset(size.width * 0.28f, size.height * 0.54f),
                    end = Offset(size.width * 0.44f, size.height * 0.70f),
                    strokeWidth = 1.8.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = tint,
                    start = Offset(size.width * 0.44f, size.height * 0.70f),
                    end = Offset(size.width * 0.72f, size.height * 0.34f),
                    strokeWidth = 1.8.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Composable
private fun BottomNavIcon(
    type: BottomNavType,
    modifier: Modifier = Modifier,
    tint: Color = CancelledNavy
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            BottomNavType.Weather -> {
                drawCircle(tint, radius = size.minDimension * 0.18f, center = Offset(size.width * 0.38f, size.height * 0.58f))
                drawCircle(tint, radius = size.minDimension * 0.23f, center = Offset(size.width * 0.56f, size.height * 0.50f))
                drawOval(tint, topLeft = Offset(size.width * 0.18f, size.height * 0.56f), size = Size(size.width * 0.64f, size.height * 0.22f))
            }
            BottomNavType.Forecast -> {
                drawLine(
                    color = tint,
                    start = Offset(size.width * 0.18f, size.height * 0.74f),
                    end = Offset(size.width * 0.42f, size.height * 0.48f),
                    strokeWidth = 1.4.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = tint,
                    start = Offset(size.width * 0.42f, size.height * 0.48f),
                    end = Offset(size.width * 0.60f, size.height * 0.58f),
                    strokeWidth = 1.4.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = tint,
                    start = Offset(size.width * 0.60f, size.height * 0.58f),
                    end = Offset(size.width * 0.82f, size.height * 0.28f),
                    strokeWidth = 1.4.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = tint,
                    start = Offset(size.width * 0.64f, size.height * 0.28f),
                    end = Offset(size.width * 0.82f, size.height * 0.28f),
                    strokeWidth = 1.4.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = tint,
                    start = Offset(size.width * 0.82f, size.height * 0.28f),
                    end = Offset(size.width * 0.82f, size.height * 0.46f),
                    strokeWidth = 1.4.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            BottomNavType.Safety -> {
                val shield = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.10f)
                    lineTo(size.width * 0.78f, size.height * 0.22f)
                    lineTo(size.width * 0.72f, size.height * 0.60f)
                    quadraticBezierTo(size.width * 0.66f, size.height * 0.80f, size.width * 0.50f, size.height * 0.92f)
                    quadraticBezierTo(size.width * 0.34f, size.height * 0.80f, size.width * 0.28f, size.height * 0.60f)
                    lineTo(size.width * 0.22f, size.height * 0.22f)
                    close()
                }
                drawPath(shield, color = tint)
            }
            BottomNavType.Settings -> {
                drawCircle(
                    color = tint,
                    radius = size.minDimension * 0.30f,
                    center = Offset(size.width / 2f, size.height / 2f),
                    style = stroke
                )
                drawCircle(
                    color = tint,
                    radius = size.minDimension * 0.09f,
                    center = Offset(size.width / 2f, size.height / 2f)
                )
                repeat(6) { index ->
                    val angle = Math.toRadians((index * 60).toDouble())
                    drawLine(
                        color = tint,
                        start = Offset(
                            size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.39f,
                            size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.39f
                        ),
                        end = Offset(
                            size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.49f,
                            size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.49f
                        ),
                        strokeWidth = 1.2.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    }
}

private enum class BottomNavType {
    Weather,
    Forecast,
    Safety,
    Settings
}

private enum class CancelInfoIcon {
    Shield,
    Check
}

private val AlertCancelledBackgroundTop = Color(0xFFF9FBFC)
private val AlertCancelledBackgroundBottom = Color(0xFFF5F7FA)
private val CancelledNavy = Color(0xFF11156D)
private val CancelledText = Color(0xFF1E212B)
private val CancelledBody = Color(0xFF6F7486)
private val CancelledGreen = Color(0xFF45D755)
private val CancelledGlow = Color(0xFF8BF07A).copy(alpha = 0.50f)
private val CancelledGreenText = Color(0xFF1F983A)
private val CancelledSoftBlue = Color(0xFFE8EDFF)
private val CancelledSoftGreen = Color(0xFFE6F9E6)
private val CancelledCardMuted = Color(0xFFF2F4F8)
private val CancelledSelectedPill = Color(0xFFE5EAFF)
private val CancelledNavMuted = Color(0xFFA0A6C8)
private val CancelledAvatarBg = Color(0xFF2B5668)
private val CancelledAvatarFace = Color(0xFFF0BF8D)
private val CancelledAvatarHair = Color(0xFF1D2A3D)
private val CancelledAvatarAccent = Color(0xFFEC6A4B)

private val CancelledTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 24.sp,
    lineHeight = 28.sp,
    fontWeight = FontWeight.ExtraBold
)

private val CancelledBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

@Preview(name = "Alert Cancelled", showBackground = true, backgroundColor = 0xFFF9FBFC)
@Composable
private fun AlertCancelledScreenPreview() {
    AmanAiTheme {
        AlertCancelledScreen()
    }
}
