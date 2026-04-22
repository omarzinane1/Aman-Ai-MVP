package com.amanai.features.safety_dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
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
fun SafetyDashboardScreen(
    modifier: Modifier = Modifier,
    onSosClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onAdvancedSettingsClick: () -> Unit = {},
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
                        SafetyBackgroundTop,
                        SafetyBackgroundBottom
                    )
                )
            )
    ) {
        SafetyDashboardHeader(
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
            Spacer(modifier = Modifier.height(12.dp))
            SafetyStatusHero()
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "System monitoring active. No security threats\ndetected in your current vicinity.",
                style = SafetyBodyStyle.copy(
                    fontSize = 9.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = SafetyBodyText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(18.dp))
            PrimaryActionCard(
                background = SafetyRed,
                title = "SOS",
                subtitle = "Emergency protocols",
                icon = SafetyActionIcon.Sos,
                onClick = onSosClick
            )
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryActionCard(
                background = SafetyBlue,
                title = "History",
                subtitle = "Recent activity logs",
                icon = SafetyActionIcon.History,
                onClick = onHistoryClick
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                text = "Quick Settings",
                style = SafetyBodyStyle.copy(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SafetyText
            )
            Spacer(modifier = Modifier.height(12.dp))
            QuickSettingCard(
                icon = SettingIconType.Location,
                title = "Stealth Location",
                subtitle = "Encrypted coordinate sharing",
                toggleOn = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            QuickSettingCard(
                icon = SettingIconType.Bell,
                title = "Panic Trigger",
                subtitle = "Double-tap power button",
                toggleOn = false
            )
            Spacer(modifier = Modifier.height(10.dp))
            AdvancedSettingsButton(onClick = onAdvancedSettingsClick)
            Spacer(modifier = Modifier.height(12.dp))
        }

        SafetyBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun SafetyDashboardHeader(modifier: Modifier = Modifier) {
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
            SmallCloudLogo(modifier = Modifier.size(width = 15.dp, height = 10.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "AMAN-AI",
                style = SafetyBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SafetyNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            HeaderAvatar()
        }
    }
}

@Composable
private fun SafetyStatusHero() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(142.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            SafetyGlow,
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )
        Surface(
            modifier = Modifier.size(102.dp),
            shape = CircleShape,
            color = Color.White,
            border = BorderStroke(2.dp, SafetyGreen),
            shadowElevation = 0.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShieldIcon(
                    modifier = Modifier.size(26.dp),
                    tint = SafetyGreenDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Safe",
                    style = SafetyBodyStyle.copy(
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = SafetyText
                )
            }
        }
    }
}

@Composable
private fun PrimaryActionCard(
    background: Color,
    title: String,
    subtitle: String,
    icon: SafetyActionIcon,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = background,
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
                    .size(28.dp)
                    .background(Color.White.copy(alpha = 0.20f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                ActionIcon(
                    type = icon,
                    modifier = Modifier.size(15.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = SafetyBodyStyle.copy(
                        fontSize = 13.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = SafetyBodyStyle.copy(
                        fontSize = 8.sp,
                        lineHeight = 11.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = Color.White.copy(alpha = 0.80f)
                )
            }
        }
    }
}

@Composable
private fun QuickSettingCard(
    icon: SettingIconType,
    title: String,
    subtitle: String,
    toggleOn: Boolean
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(SafetySettingIconBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                SettingIcon(
                    type = icon,
                    modifier = Modifier.size(13.dp),
                    tint = SafetyNavy
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = SafetyBodyStyle.copy(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = SafetyText
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = SafetyBodyStyle.copy(
                        fontSize = 8.sp,
                        lineHeight = 11.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = SafetyBodyText
                )
            }
            TogglePill(isOn = toggleOn)
        }
    }
}

@Composable
private fun TogglePill(isOn: Boolean) {
    val trackColor = if (isOn) SafetyGreen else SafetyToggleOff
    val thumbColor = if (isOn) Color.White else SafetyToggleThumbOff
    Box(
        modifier = Modifier
            .width(30.dp)
            .height(16.dp)
            .background(trackColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 2.dp),
        contentAlignment = if (isOn) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(11.dp)
                .background(thumbColor, CircleShape)
        )
    }
}

@Composable
private fun AdvancedSettingsButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = SafetyAdvancedButton,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SettingIcon(
                type = SettingIconType.Gear,
                modifier = Modifier.size(14.dp),
                tint = SafetyNavy
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Advanced Security Settings",
                style = SafetyBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SafetyNavy
            )
        }
    }
}

@Composable
private fun SafetyBottomNav(
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
            SafetyNavItem(type = BottomNavType.Weather, label = "Weather", selected = false, onClick = onWeatherClick)
            SafetyNavItem(type = BottomNavType.Forecast, label = "Forecast", selected = false, onClick = onForecastClick)
            SafetyNavItem(type = BottomNavType.Safety, label = "Safety", selected = true, onClick = onSafetyClick)
            SafetyNavItem(type = BottomNavType.Settings, label = "Settings", selected = false, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun SafetyNavItem(type: BottomNavType, label: String, selected: Boolean, onClick: () -> Unit) {
    val tint = if (selected) SafetyNavy else SafetyNavMuted
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
                    .background(SafetySelectedPill, CircleShape),
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
            style = SafetyBodyStyle.copy(
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
        drawCircle(color = SafetyAvatarBg)
        drawCircle(
            color = SafetyAvatarStroke,
            radius = size.minDimension * 0.16f,
            center = Offset(size.width * 0.50f, size.height * 0.37f),
            style = Stroke(width = 1.2.dp.toPx())
        )
        drawArc(
            color = SafetyAvatarStroke,
            startAngle = 200f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(size.width * 0.28f, size.height * 0.44f),
            size = Size(size.width * 0.44f, size.height * 0.28f),
            style = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@Composable
private fun SmallCloudLogo(modifier: Modifier = Modifier) {
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
        drawPath(path = path, color = SafetyNavy, style = stroke)
    }
}

@Composable
private fun ShieldIcon(
    modifier: Modifier = Modifier,
    tint: Color = SafetyNavy
) {
    Canvas(modifier = modifier) {
        val shield = Path().apply {
            moveTo(size.width * 0.50f, size.height * 0.08f)
            lineTo(size.width * 0.80f, size.height * 0.22f)
            lineTo(size.width * 0.73f, size.height * 0.58f)
            quadraticBezierTo(size.width * 0.67f, size.height * 0.82f, size.width * 0.50f, size.height * 0.95f)
            quadraticBezierTo(size.width * 0.33f, size.height * 0.82f, size.width * 0.27f, size.height * 0.58f)
            lineTo(size.width * 0.20f, size.height * 0.22f)
            close()
        }
        drawPath(path = shield, color = tint)
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.38f, size.height * 0.50f),
            end = Offset(size.width * 0.48f, size.height * 0.62f),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.48f, size.height * 0.62f),
            end = Offset(size.width * 0.66f, size.height * 0.40f),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun ActionIcon(type: SafetyActionIcon, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        when (type) {
            SafetyActionIcon.Sos -> {
                val strokeWidth = 1.7.dp.toPx()
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.20f, size.height * 0.50f),
                    end = Offset(size.width * 0.80f, size.height * 0.50f),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.50f, size.height * 0.20f),
                    end = Offset(size.width * 0.50f, size.height * 0.80f),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.30f, size.height * 0.30f),
                    end = Offset(size.width * 0.70f, size.height * 0.70f),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.70f, size.height * 0.30f),
                    end = Offset(size.width * 0.30f, size.height * 0.70f),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
            }
            SafetyActionIcon.History -> {
                val stroke = Stroke(width = 1.7.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
                drawArc(
                    color = Color.White,
                    startAngle = 130f,
                    sweepAngle = 250f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.14f, size.height * 0.14f),
                    size = Size(size.width * 0.72f, size.height * 0.72f),
                    style = stroke
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.36f, size.height * 0.20f),
                    end = Offset(size.width * 0.18f, size.height * 0.20f),
                    strokeWidth = 1.7.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.18f, size.height * 0.20f),
                    end = Offset(size.width * 0.18f, size.height * 0.38f),
                    strokeWidth = 1.7.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.50f, size.height * 0.34f),
                    end = Offset(size.width * 0.50f, size.height * 0.53f),
                    strokeWidth = 1.7.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.50f, size.height * 0.53f),
                    end = Offset(size.width * 0.63f, size.height * 0.60f),
                    strokeWidth = 1.7.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Composable
private fun SettingIcon(
    type: SettingIconType,
    modifier: Modifier = Modifier,
    tint: Color = SafetyNavy
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            SettingIconType.Location -> {
                val pin = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.10f)
                    cubicTo(size.width * 0.72f, size.height * 0.10f, size.width * 0.86f, size.height * 0.28f, size.width * 0.86f, size.height * 0.46f)
                    cubicTo(size.width * 0.86f, size.height * 0.64f, size.width * 0.71f, size.height * 0.80f, size.width * 0.50f, size.height * 0.92f)
                    cubicTo(size.width * 0.29f, size.height * 0.80f, size.width * 0.14f, size.height * 0.64f, size.width * 0.14f, size.height * 0.46f)
                    cubicTo(size.width * 0.14f, size.height * 0.28f, size.width * 0.28f, size.height * 0.10f, size.width * 0.50f, size.height * 0.10f)
                    close()
                }
                drawPath(pin, color = tint, style = stroke)
                drawCircle(
                    color = tint,
                    radius = size.minDimension * 0.10f,
                    center = Offset(size.width * 0.50f, size.height * 0.43f),
                    style = stroke
                )
            }
            SettingIconType.Bell -> {
                drawArc(
                    color = tint,
                    startAngle = 200f,
                    sweepAngle = 140f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.14f),
                    size = Size(size.width * 0.64f, size.height * 0.68f),
                    style = stroke
                )
                drawLine(
                    color = tint,
                    start = Offset(size.width * 0.22f, size.height * 0.64f),
                    end = Offset(size.width * 0.78f, size.height * 0.64f),
                    strokeWidth = 1.3.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawCircle(
                    color = tint,
                    radius = size.minDimension * 0.06f,
                    center = Offset(size.width * 0.50f, size.height * 0.81f)
                )
            }
            SettingIconType.Gear -> {
                drawCircle(
                    color = tint,
                    radius = size.minDimension * 0.26f,
                    center = Offset(size.width / 2f, size.height / 2f),
                    style = stroke
                )
                drawCircle(
                    color = tint,
                    radius = size.minDimension * 0.08f,
                    center = Offset(size.width / 2f, size.height / 2f)
                )
                repeat(6) { index ->
                    val angle = Math.toRadians((index * 60).toDouble())
                    drawLine(
                        color = tint,
                        start = Offset(
                            size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.34f,
                            size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.34f
                        ),
                        end = Offset(
                            size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.46f,
                            size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.46f
                        ),
                        strokeWidth = 1.1.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavIcon(
    type: BottomNavType,
    modifier: Modifier = Modifier,
    tint: Color = SafetyNavy
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

private enum class SafetyActionIcon {
    Sos,
    History
}

private enum class SettingIconType {
    Location,
    Bell,
    Gear
}

private val SafetyBackgroundTop = Color(0xFFF9FAFC)
private val SafetyBackgroundBottom = Color(0xFFF3F4F8)
private val SafetyNavy = Color(0xFF11156D)
private val SafetyText = Color(0xFF1E212B)
private val SafetyBodyText = Color(0xFF73788B)
private val SafetyGreen = Color(0xFF7CF27E)
private val SafetyGreenDark = Color(0xFF1F8E3A)
private val SafetyGlow = Color(0xFF7CF27E).copy(alpha = 0.28f)
private val SafetyRed = Color(0xFFD01A1A)
private val SafetyBlue = Color(0xFF232D98)
private val SafetyAdvancedButton = Color(0xFFF0F2F7)
private val SafetySelectedPill = Color(0xFFE5EAFF)
private val SafetyNavMuted = Color(0xFFA0A6C8)
private val SafetyToggleOff = Color(0xFFE5E6EC)
private val SafetyToggleThumbOff = Color(0xFF7E8392)
private val SafetySettingIconBg = Color(0xFFF1F2F7)
private val SafetyAvatarBg = Color(0xFF1A1A1C)
private val SafetyAvatarStroke = Color(0xFFD5D5D5)

private val SafetyBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

@Preview(name = "Safety Dashboard", showBackground = true, backgroundColor = 0xFFF9FAFC)
@Composable
private fun SafetyDashboardScreenPreview() {
    AmanAiTheme {
        SafetyDashboardScreen()
    }
}
