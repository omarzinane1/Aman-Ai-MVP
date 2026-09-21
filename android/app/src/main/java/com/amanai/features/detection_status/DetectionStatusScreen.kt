package com.amanai.features.detection_status

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
fun DetectionStatusScreen(
    modifier: Modifier = Modifier,
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DetectionBackground)
    ) {
        DetectionStatusHeader(
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
            Spacer(modifier = Modifier.height(8.dp))
            SystemIntegrityCard()
            Spacer(modifier = Modifier.height(14.dp))
            DetectionModuleCard(
                icon = DetectionModuleIcon.Microphone,
                title = "Audio Detection",
                footer = "Sensitivity: 85%",
                pulseMode = false
            )
            Spacer(modifier = Modifier.height(14.dp))
            DetectionModuleCard(
                icon = DetectionModuleIcon.Motion,
                title = "Motion Detection",
                footer = "Last scan: Now",
                pulseMode = true
            )
            Spacer(modifier = Modifier.height(14.dp))
            LiveAiAnalysisCard()
            Spacer(modifier = Modifier.height(12.dp))
        }

        DetectionBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun DetectionStatusHeader(modifier: Modifier = Modifier) {
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
                style = DetectionBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = DetectionNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            DetectionAvatar()
        }
    }
}

@Composable
private fun SystemIntegrityCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = DetectionCardTop,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            DetectionCardTop,
                            DetectionCardBottom
                        )
                    )
                )
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SYSTEM INTEGRITY",
                    style = DetectionCapsStyle.copy(
                        fontSize = 8.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.0.sp
                    ),
                    color = DetectionGreenDark
                )
                Spacer(modifier = Modifier.weight(1f))
                StatePill(text = "SECURE")
            }
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = "Risk Level: Low",
                style = DetectionBodyStyle.copy(
                    fontSize = 23.sp,
                    lineHeight = 27.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                color = DetectionText
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "The environment is stable. AI sensory\nmonitoring indicates no immediate\nbiometric or environmental threats.",
                style = DetectionBodyStyle.copy(
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = DetectionBodyMuted
            )
        }
    }
}

@Composable
private fun StatePill(text: String) {
    Row(
        modifier = Modifier
            .height(18.dp)
            .background(DetectionStatePill, RoundedCornerShape(9.dp))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(DetectionGreen, CircleShape)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            style = DetectionCapsStyle.copy(
                fontSize = 7.sp,
                lineHeight = 9.sp,
                fontWeight = FontWeight.Bold
            ),
            color = DetectionGreenDark
        )
    }
}

@Composable
private fun DetectionModuleCard(
    icon: DetectionModuleIcon,
    title: String,
    footer: String,
    pulseMode: Boolean
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (pulseMode) 148.dp else 128.dp)
                .padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(DetectionIconCircle, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    ModuleIcon(
                        type = icon,
                        modifier = Modifier.size(13.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                ActivePill()
            }
            Spacer(modifier = Modifier.height(if (pulseMode) 26.dp else 34.dp))
            Text(
                text = title,
                style = DetectionBodyStyle.copy(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = DetectionText
            )
            if (pulseMode) {
                Spacer(modifier = Modifier.height(9.dp))
                MotionPulseIndicator()
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = footer,
                style = DetectionBodyStyle.copy(
                    fontSize = 7.sp,
                    lineHeight = 9.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = DetectionFooter
            )
        }
    }
}

@Composable
private fun ActivePill() {
    Text(
        modifier = Modifier
            .height(18.dp)
            .background(DetectionStatePill, RoundedCornerShape(9.dp))
            .padding(horizontal = 10.dp, vertical = 3.dp),
        text = "ACTIVE",
        style = DetectionCapsStyle.copy(
            fontSize = 7.sp,
            lineHeight = 9.sp,
            fontWeight = FontWeight.Bold
        ),
        color = DetectionGreenDark
    )
}

@Composable
private fun MotionPulseIndicator() {
    Box(
        modifier = Modifier.size(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(DetectionPulseOuter, CircleShape)
        )
        Box(
            modifier = Modifier
                .size(14.dp)
                .background(DetectionPulseMid, CircleShape)
        )
        Box(
            modifier = Modifier
                .size(7.dp)
                .background(DetectionPulseCore, CircleShape)
        )
    }
}

@Composable
private fun LiveAiAnalysisCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = DetectionAnalysisContainer,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Live AI Analysis",
                    style = DetectionBodyStyle.copy(
                        fontSize = 13.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = DetectionText
                )
                Spacer(modifier = Modifier.weight(1f))
                RadarIcon(
                    modifier = Modifier.size(16.dp),
                    tint = DetectionPurple
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            AnalysisRow(
                title = "Acoustic fingerprint verified",
                subtitle = "Matched against \"Home Standard\" profile.",
                time = "12:44:02"
            )
            Spacer(modifier = Modifier.height(8.dp))
            AnalysisRow(
                title = "Neural path clearing",
                subtitle = "Optimizing detection threshold for\nambient wind noise.",
                time = "12:43:58"
            )
        }
    }
}

@Composable
private fun AnalysisRow(
    title: String,
    subtitle: String,
    time: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.Top
        ) {
            CheckStatusIcon(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(14.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = DetectionBodyStyle.copy(
                        fontSize = 8.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = DetectionText
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = DetectionBodyStyle.copy(
                        fontSize = 6.sp,
                        lineHeight = 9.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = DetectionBodyMuted
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = time,
                style = DetectionBodyStyle.copy(
                    fontSize = 6.sp,
                    lineHeight = 8.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = DetectionTimestamp,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun DetectionBottomNav(
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
    val tint = if (selected) DetectionNavy else DetectionNavMuted
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
                    .background(DetectionSelectedPill, CircleShape),
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
            style = DetectionBodyStyle.copy(
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
private fun DetectionAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(24.dp)) {
        drawCircle(color = DetectionAvatarBg)
        drawCircle(
            color = DetectionAvatarFace,
            radius = size.minDimension * 0.17f,
            center = Offset(size.width * 0.50f, size.height * 0.36f)
        )
        drawOval(
            color = DetectionAvatarFace,
            topLeft = Offset(size.width * 0.32f, size.height * 0.52f),
            size = Size(size.width * 0.36f, size.height * 0.20f)
        )
        drawArc(
            color = DetectionAvatarHair,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width * 0.26f, size.height * 0.12f),
            size = Size(size.width * 0.48f, size.height * 0.34f)
        )
        drawRect(
            color = DetectionAvatarAccent,
            topLeft = Offset(size.width * 0.60f, size.height * 0.54f),
            size = Size(size.width * 0.10f, size.height * 0.24f)
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
        drawPath(path = path, color = DetectionNavy, style = stroke)
    }
}

@Composable
private fun ModuleIcon(
    type: DetectionModuleIcon,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            DetectionModuleIcon.Microphone -> {
                drawRoundRect(
                    color = Color.White,
                    topLeft = Offset(size.width * 0.34f, size.height * 0.16f),
                    size = Size(size.width * 0.32f, size.height * 0.34f),
                    cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                    style = stroke
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.50f, size.height * 0.50f),
                    end = Offset(size.width * 0.50f, size.height * 0.76f),
                    strokeWidth = 1.3.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.36f, size.height * 0.76f),
                    end = Offset(size.width * 0.64f, size.height * 0.76f),
                    strokeWidth = 1.3.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawArc(
                    color = Color.White,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.22f, size.height * 0.34f),
                    size = Size(size.width * 0.56f, size.height * 0.34f),
                    style = stroke
                )
            }
            DetectionModuleIcon.Motion -> {
                drawCircle(
                    color = Color.White,
                    radius = size.minDimension * 0.09f,
                    center = Offset(size.width * 0.50f, size.height * 0.50f)
                )
                drawArc(
                    color = Color.White,
                    startAngle = 40f,
                    sweepAngle = 80f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
                    size = Size(size.width * 0.64f, size.height * 0.64f),
                    style = stroke
                )
                drawArc(
                    color = Color.White,
                    startAngle = 160f,
                    sweepAngle = 80f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
                    size = Size(size.width * 0.64f, size.height * 0.64f),
                    style = stroke
                )
                drawArc(
                    color = Color.White,
                    startAngle = 250f,
                    sweepAngle = 60f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.08f, size.height * 0.08f),
                    size = Size(size.width * 0.84f, size.height * 0.84f),
                    style = stroke
                )
            }
        }
    }
}

@Composable
private fun RadarIcon(
    modifier: Modifier = Modifier,
    tint: Color = DetectionPurple
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.28f,
            center = Offset(size.width * 0.46f, size.height * 0.50f),
            style = stroke
        )
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.10f,
            center = Offset(size.width * 0.46f, size.height * 0.50f)
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.46f, size.height * 0.50f),
            end = Offset(size.width * 0.26f, size.height * 0.28f),
            strokeWidth = 1.2.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.62f, size.height * 0.58f),
            end = Offset(size.width * 0.80f, size.height * 0.76f),
            strokeWidth = 1.2.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun CheckStatusIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = DetectionCheckBg,
            radius = size.minDimension * 0.42f,
            center = Offset(size.width / 2f, size.height / 2f)
        )
        drawLine(
            color = DetectionCheck,
            start = Offset(size.width * 0.28f, size.height * 0.52f),
            end = Offset(size.width * 0.44f, size.height * 0.68f),
            strokeWidth = 1.6.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = DetectionCheck,
            start = Offset(size.width * 0.44f, size.height * 0.68f),
            end = Offset(size.width * 0.72f, size.height * 0.34f),
            strokeWidth = 1.6.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun BottomNavIcon(
    type: BottomNavType,
    modifier: Modifier = Modifier,
    tint: Color = DetectionNavy
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

private enum class DetectionModuleIcon {
    Microphone,
    Motion
}

private val DetectionBackground = Color(0xFFF4F5F8)
private val DetectionNavy = Color(0xFF11156D)
private val DetectionText = Color(0xFF1F222A)
private val DetectionBodyMuted = Color(0xFF6F7486)
private val DetectionFooter = Color(0xFF7A7F8F)
private val DetectionCardTop = Color(0xFFEFF4EE)
private val DetectionCardBottom = Color(0xFFF2F4F2)
private val DetectionStatePill = Color(0xFFDDF7D5)
private val DetectionGreen = Color(0xFF59DF64)
private val DetectionGreenDark = Color(0xFF1F983A)
private val DetectionIconCircle = Color(0xFF23318E)
private val DetectionPulseOuter = Color(0xFFE2DFFB)
private val DetectionPulseMid = Color(0xFFBDB8F8)
private val DetectionPulseCore = Color(0xFF5B58D9)
private val DetectionAnalysisContainer = Color(0xFFEDEEF1)
private val DetectionPurple = Color(0xFF655BE7)
private val DetectionCheckBg = Color(0xFFE6F8E6)
private val DetectionCheck = Color(0xFF179B34)
private val DetectionTimestamp = Color(0xFFA3A7B5)
private val DetectionSelectedPill = Color(0xFFE5EAFF)
private val DetectionNavMuted = Color(0xFFA0A6C8)
private val DetectionAvatarBg = Color(0xFF2B5668)
private val DetectionAvatarFace = Color(0xFFF0BF8D)
private val DetectionAvatarHair = Color(0xFF1D2A3D)
private val DetectionAvatarAccent = Color(0xFFEC6A4B)

private val DetectionBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val DetectionCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 7.sp,
    lineHeight = 9.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.9.sp
)

@Preview(name = "Detection Status", showBackground = true, backgroundColor = 0xFFF4F5F8)
@Composable
private fun DetectionStatusScreenPreview() {
    AmanAiTheme {
        DetectionStatusScreen()
    }
}
