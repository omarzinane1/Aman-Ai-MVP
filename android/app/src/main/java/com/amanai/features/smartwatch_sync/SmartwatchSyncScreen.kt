package com.amanai.features.smartwatch_sync

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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
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

@Composable
fun SmartwatchSyncScreen(
    modifier: Modifier = Modifier,
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SmartwatchBackground)
    ) {
        SmartwatchHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp)
                .padding(bottom = 94.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            PairHeroCard()
            Spacer(modifier = Modifier.height(20.dp))
            ConnectionStatusCard()
            Spacer(modifier = Modifier.height(18.dp))
            DevicesHeader()
            Spacer(modifier = Modifier.height(10.dp))
            DeviceCard(
                name = "Galaxy Watch 6 Classic",
                subtitle = "Tap to connect"
            )
            Spacer(modifier = Modifier.height(10.dp))
            DeviceCard(
                name = "Apple Watch Series 9",
                subtitle = "Nearby device"
            )
            Spacer(modifier = Modifier.height(10.dp))
            DeviceCard(
                name = "Garmin Fenix 7",
                subtitle = "Saved device"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HelpCard()
            Spacer(modifier = Modifier.height(12.dp))
        }

        SmartwatchBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun SmartwatchHeader(modifier: Modifier = Modifier) {
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
                style = SmartwatchBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SmartwatchNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            HeaderAvatar()
        }
    }
}

@Composable
private fun PairHeroCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(252.dp),
        shape = RoundedCornerShape(22.dp),
        color = SmartwatchHeroBlue,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            SmartwatchHeroGlow,
                            SmartwatchHeroBlue
                        ),
                        center = Offset(520f, 210f),
                        radius = 780f
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                SmartwatchIllustration(
                    modifier = Modifier.size(width = 110.dp, height = 124.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Pair Smartwatch",
                    style = SmartwatchTitleStyle,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Sync your device for real-time safety\nmonitoring and weather alerts on your\nwrist.",
                    style = SmartwatchBodyStyle.copy(
                        fontSize = 8.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = SmartwatchHeroBody,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun SmartwatchIllustration(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strapColor = Color(0xFF222A8C)
        val strapShadow = Color(0xFF1C2378)
        val bodyColor = Color(0xFF27309A)
        val outline = Color(0xFF3942AD)
        val iconTint = Color(0xFFBFC6FF)

        drawRoundRect(
            color = strapShadow,
            topLeft = Offset(size.width * 0.36f, size.height * 0.00f),
            size = Size(size.width * 0.28f, size.height * 0.25f),
            cornerRadius = CornerRadius(14.dp.toPx(), 14.dp.toPx())
        )
        drawRoundRect(
            color = strapShadow,
            topLeft = Offset(size.width * 0.36f, size.height * 0.75f),
            size = Size(size.width * 0.28f, size.height * 0.25f),
            cornerRadius = CornerRadius(14.dp.toPx(), 14.dp.toPx())
        )
        drawRoundRect(
            color = strapColor,
            topLeft = Offset(size.width * 0.37f, size.height * 0.03f),
            size = Size(size.width * 0.26f, size.height * 0.20f),
            cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx())
        )
        drawRoundRect(
            color = strapColor,
            topLeft = Offset(size.width * 0.37f, size.height * 0.77f),
            size = Size(size.width * 0.26f, size.height * 0.20f),
            cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx())
        )
        drawRoundRect(
            color = bodyColor,
            topLeft = Offset(size.width * 0.19f, size.height * 0.10f),
            size = Size(size.width * 0.62f, size.height * 0.72f),
            cornerRadius = CornerRadius(24.dp.toPx(), 24.dp.toPx())
        )
        drawRoundRect(
            color = Color.Black.copy(alpha = 0.10f),
            topLeft = Offset(size.width * 0.22f, size.height * 0.13f),
            size = Size(size.width * 0.56f, size.height * 0.66f),
            cornerRadius = CornerRadius(20.dp.toPx(), 20.dp.toPx())
        )
        drawRoundRect(
            color = outline,
            topLeft = Offset(size.width * 0.21f, size.height * 0.11f),
            size = Size(size.width * 0.58f, size.height * 0.68f),
            cornerRadius = CornerRadius(23.dp.toPx(), 23.dp.toPx()),
            style = Stroke(width = 1.5.dp.toPx())
        )

        val iconStroke = Stroke(width = 1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawCircle(
            color = iconTint,
            radius = size.minDimension * 0.055f,
            center = Offset(size.width * 0.50f, size.height * 0.40f),
            style = iconStroke
        )
        drawLine(
            color = iconTint,
            start = Offset(size.width * 0.50f, size.height * 0.45f),
            end = Offset(size.width * 0.50f, size.height * 0.56f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawArc(
            color = iconTint,
            startAngle = 205f,
            sweepAngle = 130f,
            useCenter = false,
            topLeft = Offset(size.width * 0.40f, size.height * 0.53f),
            size = Size(size.width * 0.20f, size.height * 0.14f),
            style = iconStroke
        )
        drawCircle(Color(0xFF1CD457), radius = size.minDimension * 0.018f, center = Offset(size.width * 0.44f, size.height * 0.67f))
        drawCircle(Color(0xFF5E6BCB), radius = size.minDimension * 0.018f, center = Offset(size.width * 0.50f, size.height * 0.67f))
        drawCircle(Color(0xFF8390DE), radius = size.minDimension * 0.018f, center = Offset(size.width * 0.56f, size.height * 0.67f))
    }
}

@Composable
private fun ConnectionStatusCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = SmartwatchCard,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .background(SmartwatchDisconnectedDot, CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "CONNECTION STATUS",
                    style = SmartwatchCapsStyle.copy(
                        fontSize = 6.sp,
                        lineHeight = 8.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.7.sp
                    ),
                    color = SmartwatchSectionText
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Disconnected",
                    style = SmartwatchBodyStyle.copy(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = SmartwatchText
                )
            }
            Text(
                text = "Refresh",
                style = SmartwatchBodyStyle.copy(
                    fontSize = 9.sp,
                    lineHeight = 10.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SmartwatchNavy
            )
        }
    }
}

@Composable
private fun DevicesHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Available Devices",
            style = SmartwatchBodyStyle.copy(
                fontSize = 11.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            color = SmartwatchText
        )
        Spacer(modifier = Modifier.weight(1f))
        RefreshIcon(
            modifier = Modifier.size(14.dp),
            tint = SmartwatchRefresh
        )
    }
}

@Composable
private fun DeviceCard(
    name: String,
    subtitle: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = SmartwatchCard,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                DeviceWatchIcon(
                    modifier = Modifier.size(14.dp),
                    tint = SmartwatchNavy
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    style = SmartwatchBodyStyle.copy(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = SmartwatchText
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = subtitle,
                    style = SmartwatchBodyStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 9.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = SmartwatchBody
                )
            }
            ChevronIcon(
                modifier = Modifier.size(12.dp),
                tint = SmartwatchChevron
            )
        }
    }
}

@Composable
private fun HelpCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.Transparent,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = SmartwatchDashedBorder,
                    cornerRadius = CornerRadius(20.dp.toPx(), 20.dp.toPx()),
                    style = Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f))
                    )
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 16.dp),
                verticalAlignment = Alignment.Top
            ) {
                HelpIcon(
                    modifier = Modifier
                        .padding(top = 1.dp)
                        .size(13.dp),
                    tint = SmartwatchNavy
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Don't see your device?",
                        style = SmartwatchBodyStyle.copy(
                            fontSize = 9.sp,
                            lineHeight = 11.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = SmartwatchText
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Ensure your smartwatch is in pairing mode\nand Bluetooth is enabled on both devices.",
                        style = SmartwatchBodyStyle.copy(
                            fontSize = 7.sp,
                            lineHeight = 10.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = SmartwatchBody
                    )
                }
            }
        }
    }
}

@Composable
private fun SmartwatchBottomNav(
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
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        color = Color.White,
        shadowElevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            BottomNavItem(type = BottomNavType.Weather, label = "Weather", selected = false, onClick = onWeatherClick)
            BottomNavItem(type = BottomNavType.Forecast, label = "Forecast", selected = false, onClick = onForecastClick)
            BottomNavItem(type = BottomNavType.Safety, label = "Safety", selected = false, onClick = onSafetyClick)
            BottomNavItem(type = BottomNavType.Settings, label = "Settings", selected = true, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun BottomNavItem(type: BottomNavType, label: String, selected: Boolean, onClick: () -> Unit) {
    val tint = if (selected) SmartwatchNavy else SmartwatchNavMuted
    Column(
        modifier = Modifier
            .widthIn(min = 40.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(width = 37.dp, height = 28.dp)
                    .background(SmartwatchSelectedPill, CircleShape),
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
            style = SmartwatchBodyStyle.copy(
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
private fun HeaderAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(22.dp)) {
        drawCircle(color = SmartwatchAvatarBg)
        drawCircle(
            color = SmartwatchAvatarFace,
            radius = size.minDimension * 0.17f,
            center = Offset(size.width * 0.50f, size.height * 0.35f)
        )
        drawOval(
            color = SmartwatchAvatarFace,
            topLeft = Offset(size.width * 0.32f, size.height * 0.52f),
            size = Size(size.width * 0.36f, size.height * 0.20f)
        )
        drawArc(
            color = SmartwatchAvatarHair,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width * 0.26f, size.height * 0.10f),
            size = Size(size.width * 0.48f, size.height * 0.32f)
        )
        drawRect(
            color = SmartwatchAvatarAccent,
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
        drawPath(path = path, color = SmartwatchNavy, style = stroke)
    }
}

@Composable
private fun DeviceWatchIcon(modifier: Modifier = Modifier, tint: Color = SmartwatchNavy) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawRoundRect(
            color = tint,
            topLeft = Offset(size.width * 0.34f, size.height * 0.08f),
            size = Size(size.width * 0.32f, size.height * 0.16f),
            cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
        )
        drawRoundRect(
            color = tint,
            topLeft = Offset(size.width * 0.34f, size.height * 0.76f),
            size = Size(size.width * 0.32f, size.height * 0.16f),
            cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
        )
        drawRoundRect(
            color = tint,
            topLeft = Offset(size.width * 0.22f, size.height * 0.22f),
            size = Size(size.width * 0.56f, size.height * 0.52f),
            cornerRadius = CornerRadius(7.dp.toPx(), 7.dp.toPx()),
            style = stroke
        )
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.06f,
            center = Offset(size.width * 0.50f, size.height * 0.48f)
        )
    }
}

@Composable
private fun RefreshIcon(modifier: Modifier = Modifier, tint: Color = SmartwatchRefresh) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawArc(
            color = tint,
            startAngle = 110f,
            sweepAngle = 220f,
            useCenter = false,
            topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
            size = Size(size.width * 0.64f, size.height * 0.64f),
            style = stroke
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.64f, size.height * 0.20f),
            end = Offset(size.width * 0.82f, size.height * 0.24f),
            strokeWidth = 1.3.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.82f, size.height * 0.24f),
            end = Offset(size.width * 0.74f, size.height * 0.40f),
            strokeWidth = 1.3.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun HelpIcon(modifier: Modifier = Modifier, tint: Color = SmartwatchNavy) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.40f,
            center = Offset(size.width / 2f, size.height / 2f),
            style = Stroke(width = 1.2.dp.toPx())
        )
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.05f,
            center = Offset(size.width * 0.50f, size.height * 0.72f)
        )
        drawArc(
            color = tint,
            startAngle = 210f,
            sweepAngle = 120f,
            useCenter = false,
            topLeft = Offset(size.width * 0.30f, size.height * 0.18f),
            size = Size(size.width * 0.40f, size.height * 0.28f),
            style = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round)
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.50f, size.height * 0.42f),
            end = Offset(size.width * 0.50f, size.height * 0.58f),
            strokeWidth = 1.2.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun ChevronIcon(modifier: Modifier = Modifier, tint: Color = SmartwatchChevron) {
    Canvas(modifier = modifier) {
        drawLine(
            color = tint,
            start = Offset(size.width * 0.32f, size.height * 0.22f),
            end = Offset(size.width * 0.68f, size.height * 0.50f),
            strokeWidth = 1.4.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.68f, size.height * 0.50f),
            end = Offset(size.width * 0.32f, size.height * 0.78f),
            strokeWidth = 1.4.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun BottomNavIcon(type: BottomNavType, modifier: Modifier = Modifier, tint: Color = SmartwatchNavy) {
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

private val SmartwatchBackground = Color(0xFFF9FAFC)
private val SmartwatchNavy = Color(0xFF11156D)
private val SmartwatchText = Color(0xFF1E212B)
private val SmartwatchBody = Color(0xFF72778C)
private val SmartwatchHeroBlue = Color(0xFF27309A)
private val SmartwatchHeroGlow = Color(0xFF3340B5)
private val SmartwatchHeroBody = Color(0xFFAAB1EE)
private val SmartwatchCard = Color(0xFFF4F5F7)
private val SmartwatchDisconnectedDot = Color(0xFFD8332A)
private val SmartwatchSectionText = Color(0xFF8A8FA6)
private val SmartwatchRefresh = Color(0xFF11156D)
private val SmartwatchChevron = Color(0xFF9398AB)
private val SmartwatchDashedBorder = Color(0xFFD8DBE6)
private val SmartwatchSelectedPill = Color(0xFFE5EAFF)
private val SmartwatchNavMuted = Color(0xFFA0A6C8)
private val SmartwatchAvatarBg = Color(0xFF31475D)
private val SmartwatchAvatarFace = Color(0xFFF0BF8D)
private val SmartwatchAvatarHair = Color(0xFF1D2A3D)
private val SmartwatchAvatarAccent = Color(0xFFF3C45B)

private val SmartwatchTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 19.sp,
    lineHeight = 23.sp,
    fontWeight = FontWeight.ExtraBold
)

private val SmartwatchBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val SmartwatchCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 7.sp,
    lineHeight = 9.sp,
    fontWeight = FontWeight.Medium
)

@Preview(name = "Smartwatch Sync", showBackground = true, backgroundColor = 0xFFF9FAFC)
@Composable
private fun SmartwatchSyncScreenPreview() {
    AmanAiTheme {
        SmartwatchSyncScreen()
    }
}
