package com.amanai.features.alert_sent_status

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
import androidx.compose.ui.graphics.drawscope.DrawScope
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
fun AlertSentStatusScreen(
    modifier: Modifier = Modifier,
    onEndAlert: () -> Unit = {},
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
                        AlertSentBackgroundTop,
                        AlertSentBackgroundBottom
                    )
                )
            )
    ) {
        AlertSentHeader(
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
            Spacer(modifier = Modifier.height(6.dp))
            SentHero()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Alert Sent to 3 Contacts",
                style = AlertSentTitleStyle,
                color = AlertSentNavy,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            TimestampRow()
            Spacer(modifier = Modifier.height(10.dp))
            AssistancePill()
            Spacer(modifier = Modifier.height(14.dp))
            LocationPreviewCard()
            Spacer(modifier = Modifier.height(12.dp))
            ContactCard(
                accent = AlertSentContactLight,
                title = "Sarah J. (Wife)",
                subtitle = "Alert viewed via SMS & App",
                dark = false,
                trailing = ContactTrailing.Check
            )
            Spacer(modifier = Modifier.height(10.dp))
            ContactCard(
                accent = AlertSentNavy,
                title = "Michael R. (Brother)",
                subtitle = "SMS delivered to trusted contact",
                dark = true,
                trailing = ContactTrailing.Viewing
            )
            Spacer(modifier = Modifier.height(10.dp))
            EmergencyServicesCard()
            Spacer(modifier = Modifier.height(16.dp))
            CallPrimaryButton()
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "I'm Safe Now (End Alert)",
                modifier = Modifier.clickable(onClick = onEndAlert),
                style = AlertSentBodyStyle.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = AlertSentNavy,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        AlertSentBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun AlertSentHeader(modifier: Modifier = Modifier) {
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
                style = AlertSentBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = AlertSentNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            UserAvatar()
        }
    }
}

@Composable
private fun SentHero() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(92.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            AlertSentGlow,
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(AlertSentGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            ShieldIcon(
                modifier = Modifier.size(18.dp),
                tint = AlertSentShield
            )
        }
    }
}

@Composable
private fun TimestampRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClockIcon(
            modifier = Modifier.size(10.dp),
            tint = AlertSentMuted
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "14:32 - October 24, 2023",
            style = AlertSentBodyStyle.copy(
                fontSize = 7.sp,
                lineHeight = 9.sp,
                fontWeight = FontWeight.Medium
            ),
            color = AlertSentMuted
        )
    }
}

@Composable
private fun AssistancePill() {
    Row(
        modifier = Modifier
            .height(22.dp)
            .background(AlertSentPill, RoundedCornerShape(11.dp))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(AlertSentGreenDot, CircleShape)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "WAITING FOR ASSISTANCE",
            style = AlertSentCapsStyle.copy(
                fontSize = 7.sp,
                lineHeight = 9.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.9.sp
            ),
            color = AlertSentGreenText
        )
    }
}

@Composable
private fun LocationPreviewCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AlertSentMapTop,
                            AlertSentMapBottom
                        )
                    )
                )
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLocationTexture()
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 10.dp, bottom = 10.dp)
                    .background(Color.White.copy(alpha = 0.92f), RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(AlertSentSoftBlue, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    PinIcon(
                        modifier = Modifier.size(6.dp),
                        tint = AlertSentNavy
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Your location shared",
                    style = AlertSentBodyStyle.copy(
                        fontSize = 6.sp,
                        lineHeight = 8.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = AlertSentNavy
                )
            }
        }
    }
}

private fun DrawScope.drawLocationTexture() {
    val thin = 1.2.dp.toPx()
    val thick = 1.8.dp.toPx()
    drawLine(
        color = AlertSentMapLine.copy(alpha = 0.34f),
        start = Offset(size.width * 0.08f, size.height * 0.15f),
        end = Offset(size.width * 0.88f, size.height * 0.84f),
        strokeWidth = thick,
        cap = StrokeCap.Round
    )
    drawLine(
        color = AlertSentMapLine.copy(alpha = 0.26f),
        start = Offset(size.width * 0.10f, size.height * 0.84f),
        end = Offset(size.width * 0.90f, size.height * 0.18f),
        strokeWidth = thick,
        cap = StrokeCap.Round
    )
    repeat(7) { index ->
        val x = size.width * (0.10f + index * 0.12f)
        drawLine(
            color = AlertSentMapLine.copy(alpha = 0.20f),
            start = Offset(x, size.height * 0.05f),
            end = Offset(x + size.width * 0.12f, size.height * 0.95f),
            strokeWidth = thin,
            cap = StrokeCap.Round
        )
    }
    repeat(5) { index ->
        val y = size.height * (0.14f + index * 0.18f)
        drawLine(
            color = AlertSentMapLine.copy(alpha = 0.18f),
            start = Offset(size.width * 0.02f, y),
            end = Offset(size.width * 0.98f, y + size.height * 0.10f),
            strokeWidth = thin,
            cap = StrokeCap.Round
        )
    }
    val wave = Path().apply {
        moveTo(size.width * 0.00f, size.height * 0.62f)
        cubicTo(
            size.width * 0.20f, size.height * 0.44f,
            size.width * 0.42f, size.height * 0.82f,
            size.width * 0.62f, size.height * 0.56f
        )
        cubicTo(
            size.width * 0.78f, size.height * 0.34f,
            size.width * 0.90f, size.height * 0.50f,
            size.width, size.height * 0.30f
        )
    }
    drawPath(
        path = wave,
        color = AlertSentMapLine.copy(alpha = 0.22f),
        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    )
}

@Composable
private fun ContactCard(
    accent: Color,
    title: String,
    subtitle: String,
    dark: Boolean,
    trailing: ContactTrailing
) {
    val titleColor = if (dark) Color.White else AlertSentText
    val subtitleColor = if (dark) Color.White.copy(alpha = 0.72f) else AlertSentMuted

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = accent,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ContactAvatar(
                modifier = Modifier.size(28.dp),
                dark = dark
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = AlertSentBodyStyle.copy(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = titleColor
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = AlertSentBodyStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 9.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = subtitleColor
                )
            }
            when (trailing) {
                ContactTrailing.Check -> {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(AlertSentGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        CheckIcon(
                            modifier = Modifier.size(9.dp),
                            tint = Color.White
                        )
                    }
                }
                ContactTrailing.Viewing -> {
                    Row(
                        modifier = Modifier
                            .height(16.dp)
                            .background(AlertSentViewingPill, RoundedCornerShape(8.dp))
                            .padding(horizontal = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        EyeIcon(
                            modifier = Modifier.size(8.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "VIEWING",
                            style = AlertSentCapsStyle.copy(
                                fontSize = 6.sp,
                                lineHeight = 8.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.7.sp
                            ),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmergencyServicesCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = AlertSentEmergencyCard,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .background(AlertSentEmergencySoft, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                PhoneMarkerIcon(
                    modifier = Modifier.size(11.dp),
                    tint = AlertSentEmergencyRed
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Emergency Services",
                    style = AlertSentBodyStyle.copy(
                        fontSize = 9.sp,
                        lineHeight = 11.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = AlertSentNavy
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Ready to call local authorities",
                    style = AlertSentBodyStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 9.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = AlertSentMuted
                )
            }
            ChevronIcon(
                modifier = Modifier.size(12.dp),
                tint = AlertSentMuted
            )
        }
    }
}

@Composable
private fun CallPrimaryButton() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = RoundedCornerShape(20.dp),
        color = AlertSentNavy,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PhoneOutlineIcon(
                modifier = Modifier.size(12.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = "Call Primary Contact",
                style = AlertSentBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
    }
}

@Composable
private fun AlertSentBottomNav(
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
    val tint = if (selected) AlertSentNavy else AlertSentNavMuted
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
                    .background(AlertSentSelectedPill, CircleShape),
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
            style = AlertSentBodyStyle.copy(
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
        drawCircle(color = AlertSentAvatarBg)
        drawCircle(
            color = AlertSentAvatarFace,
            radius = size.minDimension * 0.17f,
            center = Offset(size.width * 0.50f, size.height * 0.35f)
        )
        drawOval(
            color = AlertSentAvatarFace,
            topLeft = Offset(size.width * 0.32f, size.height * 0.52f),
            size = Size(size.width * 0.36f, size.height * 0.20f)
        )
        drawArc(
            color = AlertSentAvatarHair,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width * 0.26f, size.height * 0.10f),
            size = Size(size.width * 0.48f, size.height * 0.32f)
        )
        drawRect(
            color = AlertSentAvatarAccent,
            topLeft = Offset(size.width * 0.60f, size.height * 0.52f),
            size = Size(size.width * 0.10f, size.height * 0.24f)
        )
    }
}

@Composable
private fun ContactAvatar(modifier: Modifier = Modifier, dark: Boolean) {
    val bg = if (dark) Color(0xFF1A1D66) else Color(0xFFF0C89A)
    val face = if (dark) Color(0xFFF2C28F) else Color(0xFFF6D3AD)
    val hair = if (dark) Color(0xFF111126) else Color(0xFF7B4C2C)

    Canvas(modifier = modifier) {
        drawCircle(color = bg)
        drawCircle(
            color = face,
            radius = size.minDimension * 0.20f,
            center = Offset(size.width * 0.50f, size.height * 0.36f)
        )
        drawOval(
            color = face,
            topLeft = Offset(size.width * 0.30f, size.height * 0.55f),
            size = Size(size.width * 0.40f, size.height * 0.20f)
        )
        drawArc(
            color = hair,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width * 0.24f, size.height * 0.12f),
            size = Size(size.width * 0.52f, size.height * 0.34f)
        )
        if (!dark) {
            drawRect(
                color = Color(0xFF0B5C2E),
                topLeft = Offset(size.width * 0.14f, size.height * 0.48f),
                size = Size(size.width * 0.10f, size.height * 0.26f)
            )
        }
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
        drawPath(path = path, color = AlertSentNavy, style = stroke)
    }
}

@Composable
private fun ShieldIcon(modifier: Modifier = Modifier, tint: Color = AlertSentShield) {
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
        drawCircle(
            color = Color.White,
            radius = size.minDimension * 0.09f,
            center = Offset(size.width * 0.50f, size.height * 0.44f)
        )
    }
}

@Composable
private fun ClockIcon(modifier: Modifier = Modifier, tint: Color = AlertSentMuted) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.1.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.40f,
            center = Offset(size.width / 2f, size.height / 2f),
            style = stroke
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.50f, size.height * 0.50f),
            end = Offset(size.width * 0.50f, size.height * 0.28f),
            strokeWidth = 1.1.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.50f, size.height * 0.50f),
            end = Offset(size.width * 0.66f, size.height * 0.58f),
            strokeWidth = 1.1.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun PinIcon(modifier: Modifier = Modifier, tint: Color = AlertSentNavy) {
    Canvas(modifier = modifier) {
        val pin = Path().apply {
            moveTo(size.width * 0.50f, size.height * 0.10f)
            cubicTo(size.width * 0.72f, size.height * 0.10f, size.width * 0.86f, size.height * 0.28f, size.width * 0.86f, size.height * 0.46f)
            cubicTo(size.width * 0.86f, size.height * 0.64f, size.width * 0.71f, size.height * 0.80f, size.width * 0.50f, size.height * 0.92f)
            cubicTo(size.width * 0.29f, size.height * 0.80f, size.width * 0.14f, size.height * 0.64f, size.width * 0.14f, size.height * 0.46f)
            cubicTo(size.width * 0.14f, size.height * 0.28f, size.width * 0.28f, size.height * 0.10f, size.width * 0.50f, size.height * 0.10f)
            close()
        }
        drawPath(pin, color = tint)
    }
}

@Composable
private fun CheckIcon(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier) {
        drawLine(
            color = tint,
            start = Offset(size.width * 0.20f, size.height * 0.54f),
            end = Offset(size.width * 0.40f, size.height * 0.74f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.40f, size.height * 0.74f),
            end = Offset(size.width * 0.78f, size.height * 0.28f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun EyeIcon(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.1.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        val outline = Path().apply {
            moveTo(size.width * 0.10f, size.height * 0.50f)
            quadraticBezierTo(size.width * 0.50f, size.height * 0.16f, size.width * 0.90f, size.height * 0.50f)
            quadraticBezierTo(size.width * 0.50f, size.height * 0.84f, size.width * 0.10f, size.height * 0.50f)
            close()
        }
        drawPath(outline, color = tint, style = stroke)
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.12f,
            center = Offset(size.width * 0.50f, size.height * 0.50f)
        )
    }
}

@Composable
private fun PhoneMarkerIcon(modifier: Modifier = Modifier, tint: Color = AlertSentEmergencyRed) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.40f,
            center = Offset(size.width * 0.50f, size.height * 0.32f)
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.50f, size.height * 0.72f),
            end = Offset(size.width * 0.50f, size.height * 0.94f),
            strokeWidth = 1.2.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawRoundRect(
            color = Color.White,
            topLeft = Offset(size.width * 0.38f, size.height * 0.18f),
            size = Size(size.width * 0.24f, size.height * 0.20f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx(), 2.dp.toPx())
        )
    }
}

@Composable
private fun PhoneOutlineIcon(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawArc(
            color = tint,
            startAngle = 130f,
            sweepAngle = 100f,
            useCenter = false,
            topLeft = Offset(size.width * 0.12f, size.height * 0.08f),
            size = Size(size.width * 0.40f, size.height * 0.70f),
            style = stroke
        )
        drawArc(
            color = tint,
            startAngle = 310f,
            sweepAngle = 100f,
            useCenter = false,
            topLeft = Offset(size.width * 0.48f, size.height * 0.22f),
            size = Size(size.width * 0.36f, size.height * 0.52f),
            style = stroke
        )
    }
}

@Composable
private fun ChevronIcon(modifier: Modifier = Modifier, tint: Color = AlertSentMuted) {
    Canvas(modifier = modifier) {
        drawLine(
            color = tint,
            start = Offset(size.width * 0.32f, size.height * 0.22f),
            end = Offset(size.width * 0.68f, size.height * 0.50f),
            strokeWidth = 1.5.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.68f, size.height * 0.50f),
            end = Offset(size.width * 0.32f, size.height * 0.78f),
            strokeWidth = 1.5.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun BottomNavIcon(type: BottomNavType, modifier: Modifier = Modifier, tint: Color = AlertSentNavy) {
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

private enum class ContactTrailing {
    Check,
    Viewing
}

private val AlertSentBackgroundTop = Color(0xFFF9FBFC)
private val AlertSentBackgroundBottom = Color(0xFFF5F7FA)
private val AlertSentNavy = Color(0xFF11156D)
private val AlertSentText = Color(0xFF1F222B)
private val AlertSentMuted = Color(0xFF7A7F92)
private val AlertSentGreen = Color(0xFF89F26F)
private val AlertSentGlow = Color(0xFF8BF07A).copy(alpha = 0.45f)
private val AlertSentShield = Color(0xFF106D2E)
private val AlertSentPill = Color(0xFFEAF6E7)
private val AlertSentGreenDot = Color(0xFF36C758)
private val AlertSentGreenText = Color(0xFF258441)
private val AlertSentMapTop = Color(0xFFD7DAE0)
private val AlertSentMapBottom = Color(0xFFBFC6D1)
private val AlertSentMapLine = Color(0xFFFFFFFF)
private val AlertSentSoftBlue = Color(0xFFE9EDFF)
private val AlertSentContactLight = Color(0xFFF3F5F7)
private val AlertSentViewingPill = Color(0xFF6FD866)
private val AlertSentEmergencyCard = Color(0xFFF4F4F6)
private val AlertSentEmergencySoft = Color(0xFFFFE7E7)
private val AlertSentEmergencyRed = Color(0xFFEC4B42)
private val AlertSentSelectedPill = Color(0xFFE5EAFF)
private val AlertSentNavMuted = Color(0xFFA0A6C8)
private val AlertSentAvatarBg = Color(0xFF2B5668)
private val AlertSentAvatarFace = Color(0xFFF0BF8D)
private val AlertSentAvatarHair = Color(0xFF1D2A3D)
private val AlertSentAvatarAccent = Color(0xFFEC6A4B)

private val AlertSentTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 17.sp,
    lineHeight = 21.sp,
    fontWeight = FontWeight.ExtraBold
)

private val AlertSentBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val AlertSentCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 7.sp,
    lineHeight = 9.sp,
    fontWeight = FontWeight.Medium
)

@Preview(name = "Alert Sent Status", showBackground = true, backgroundColor = 0xFFF9FBFC)
@Composable
private fun AlertSentStatusScreenPreview() {
    AmanAiTheme {
        AlertSentStatusScreen()
    }
}
