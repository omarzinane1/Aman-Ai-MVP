package com.amanai.features.alert_history

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
fun AlertHistoryScreen(
    modifier: Modifier = Modifier,
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
                        AlertHistoryBackgroundTop,
                        AlertHistoryBackgroundBottom
                    )
                )
            )
    ) {
        HistoryHeader(
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
                .padding(bottom = 92.dp)
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Alert history",
                    style = HistoryTitleStyle,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                EventsPill()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Review all detected security events and\nautomated system responses from the last 30\ndays.",
                style = HistoryBodyStyle.copy(
                    fontSize = 9.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = AlertHistoryBody
            )
            Spacer(modifier = Modifier.height(16.dp))
            FilterRow()
            Spacer(modifier = Modifier.height(14.dp))
            DividerLine()
            Spacer(modifier = Modifier.height(10.dp))
            SectionLabel("TODAY")
            Spacer(modifier = Modifier.height(10.dp))
            HistoryEventCard(
                iconType = HistoryEventIcon.Burst,
                iconBackground = AlertHistorySoftPink,
                iconTint = AlertHistoryCritical,
                title = "Unauthorized\nEntry Attempt",
                time = "14:22\nPM",
                subtitle = "Back Entrance - Zone 04,\nAutomated lockdown\ninitiated.",
                background = AlertHistoryCardOne,
                badges = listOf(
                    EventBadge("CRITICAL", AlertHistoryCriticalBadge, Color.White),
                    EventBadge("LOCKED", AlertHistoryMutedBadge, AlertHistoryMutedBadgeText)
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            HistoryEventCard(
                iconType = HistoryEventIcon.Motion,
                iconBackground = AlertHistorySoftLavender,
                iconTint = AlertHistoryIconLavender,
                title = "Motion Alert -\nCanceled",
                time = "12:05\nPM",
                subtitle = "Living Room, identified as\nDomestic Pet (Max).",
                background = AlertHistoryCardTwo,
                badges = listOf(
                    EventBadge("RESOLVED", AlertHistoryResolvedBadge, AlertHistoryResolvedText)
                )
            )
            Spacer(modifier = Modifier.height(14.dp))
            SectionLabel("YESTERDAY")
            Spacer(modifier = Modifier.height(10.dp))
            HistoryEventCard(
                iconType = HistoryEventIcon.Node,
                iconBackground = AlertHistorySoftIndigo,
                iconTint = AlertHistoryIconIndigo,
                title = "System\nOptimization",
                time = "21:40\nPM",
                subtitle = "Internal database sync\ncompleted. Security patches\napplied.",
                background = AlertHistoryCardThree,
                badges = listOf(
                    EventBadge("AUTO-UPDATE", AlertHistoryAutoBadge, AlertHistoryAutoText)
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            HistoryEventCard(
                iconType = HistoryEventIcon.Warning,
                iconBackground = AlertHistorySoftWarning,
                iconTint = AlertHistoryWarning,
                title = "Perimeter Breach\nDetected",
                time = "18:15\nPM",
                subtitle = "Fence Line North. Motion\ndetected near gateway.",
                background = AlertHistoryCardFour,
                badges = listOf(
                    EventBadge("WARNING", AlertHistoryWarningBadge, Color.White)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Security Insights",
                style = HistoryBodyStyle.copy(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            InsightsChartCard()
            Spacer(modifier = Modifier.height(12.dp))
            SystemsReadyCard()
            Spacer(modifier = Modifier.height(12.dp))
        }

        HistoryBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun HistoryHeader(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp),
        color = AlertHistoryHeader,
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
                style = HistoryBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = AlertHistoryNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            UserAvatar()
        }
    }
}

@Composable
private fun EventsPill() {
    Box(
        modifier = Modifier
            .height(16.dp)
            .background(AlertHistoryEventsPill, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "32 Events",
            style = HistoryBodyStyle.copy(
                fontSize = 6.sp,
                lineHeight = 8.sp,
                fontWeight = FontWeight.Bold
            ),
            color = AlertHistoryEventsText
        )
    }
}

@Composable
private fun FilterRow() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip("All Alerts", selected = true)
        FilterChip("Motion", selected = false)
        FilterChip("Security", selected = false)
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row {
        FilterChip("System", selected = false)
    }
}

@Composable
private fun FilterChip(label: String, selected: Boolean) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (selected) Color.White else AlertHistoryFilterBg,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .height(24.dp)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = HistoryBodyStyle.copy(
                    fontSize = 8.sp,
                    lineHeight = 10.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = if (selected) AlertHistoryNavy else AlertHistoryFilterText
            )
        }
    }
}

@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(AlertHistoryDivider)
    )
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = HistoryCapsStyle.copy(
            fontSize = 7.sp,
            lineHeight = 9.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.0.sp
        ),
        color = AlertHistorySection
    )
}

@Composable
private fun HistoryEventCard(
    iconType: HistoryEventIcon,
    iconBackground: Color,
    iconTint: Color,
    title: String,
    time: String,
    subtitle: String,
    background: Color,
    badges: List<EventBadge>
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = background,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .size(26.dp)
                    .background(iconBackground, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                EventIcon(
                    type = iconType,
                    modifier = Modifier.size(13.dp),
                    tint = iconTint
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = title,
                        style = HistoryBodyStyle.copy(
                            fontSize = 11.sp,
                            lineHeight = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = time,
                        style = HistoryBodyStyle.copy(
                            fontSize = 6.sp,
                            lineHeight = 8.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = AlertHistoryTime,
                        textAlign = TextAlign.End
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = subtitle,
                    style = HistoryBodyStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = AlertHistoryBody
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    badges.forEach { badge ->
                        EventBadgeView(badge)
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            ChevronIcon(
                modifier = Modifier
                    .padding(top = 26.dp)
                    .size(12.dp),
                tint = AlertHistoryChevron
            )
        }
    }
}

@Composable
private fun EventBadgeView(badge: EventBadge) {
    Box(
        modifier = Modifier
            .height(14.dp)
            .background(badge.background, RoundedCornerShape(7.dp))
            .padding(horizontal = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = badge.label,
            style = HistoryCapsStyle.copy(
                fontSize = 5.sp,
                lineHeight = 7.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            ),
            color = badge.textColor
        )
    }
}

@Composable
private fun InsightsChartCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp),
        shape = RoundedCornerShape(18.dp),
        color = AlertHistoryInsightCard,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                drawInsightWaves()
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 14.dp, bottom = 14.dp)
            ) {
                Text(
                    text = "98.2%",
                    style = HistoryBodyStyle.copy(
                        fontSize = 22.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color.White
                )
                Text(
                    text = "SAFETY RATING THIS WEEK",
                    style = HistoryCapsStyle.copy(
                        fontSize = 5.sp,
                        lineHeight = 7.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.9.sp
                    ),
                    color = AlertHistoryInsightText
                )
            }
        }
    }
}

private fun DrawScope.drawInsightWaves() {
    val waveOne = Path().apply {
        moveTo(size.width * 0.00f, size.height * 0.68f)
        cubicTo(
            size.width * 0.22f, size.height * 0.48f,
            size.width * 0.34f, size.height * 0.78f,
            size.width * 0.52f, size.height * 0.56f
        )
        cubicTo(
            size.width * 0.66f, size.height * 0.40f,
            size.width * 0.82f, size.height * 0.62f,
            size.width, size.height * 0.28f
        )
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }
    drawPath(
        path = waveOne,
        brush = Brush.linearGradient(
            colors = listOf(
                AlertHistoryWaveStart,
                AlertHistoryWaveMid,
                AlertHistoryWaveEnd
            )
        )
    )
    val strokePath = Path().apply {
        moveTo(size.width * 0.04f, size.height * 0.60f)
        cubicTo(
            size.width * 0.24f, size.height * 0.44f,
            size.width * 0.40f, size.height * 0.74f,
            size.width * 0.56f, size.height * 0.50f
        )
        cubicTo(
            size.width * 0.70f, size.height * 0.30f,
            size.width * 0.84f, size.height * 0.52f,
            size.width * 0.98f, size.height * 0.20f
        )
    }
    drawPath(
        path = strokePath,
        color = AlertHistoryWaveStroke,
        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    )
}

@Composable
private fun SystemsReadyCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = AlertHistorySystemCard,
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
                    .size(18.dp)
                    .background(AlertHistorySystemIconBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                ShieldIcon(
                    modifier = Modifier.size(10.dp),
                    tint = AlertHistorySystemIcon
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "All systems are currently operational and\nsecure.",
                style = HistoryBodyStyle.copy(
                    fontSize = 8.sp,
                    lineHeight = 11.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = AlertHistorySystemText
            )
        }
    }
}

@Composable
private fun HistoryBottomNav(
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
        color = AlertHistoryNav,
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
    val tint = if (selected) AlertHistoryNavy else AlertHistoryNavMuted
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
                    .background(AlertHistorySelectedPill, CircleShape),
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
            style = HistoryBodyStyle.copy(
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
        drawCircle(color = AlertHistoryAvatarBg)
        drawCircle(
            color = AlertHistoryAvatarFace,
            radius = size.minDimension * 0.17f,
            center = Offset(size.width * 0.50f, size.height * 0.35f)
        )
        drawOval(
            color = AlertHistoryAvatarFace,
            topLeft = Offset(size.width * 0.32f, size.height * 0.52f),
            size = Size(size.width * 0.36f, size.height * 0.20f)
        )
        drawArc(
            color = AlertHistoryAvatarHair,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width * 0.26f, size.height * 0.10f),
            size = Size(size.width * 0.48f, size.height * 0.32f)
        )
        drawRect(
            color = AlertHistoryAvatarAccent,
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
        drawPath(path = path, color = AlertHistoryNavy, style = stroke)
    }
}

@Composable
private fun ShieldIcon(modifier: Modifier = Modifier, tint: Color = Color.White) {
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
    }
}

@Composable
private fun EventIcon(type: HistoryEventIcon, modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier) {
        when (type) {
            HistoryEventIcon.Burst -> {
                val strokeWidth = 1.6.dp.toPx()
                drawLine(tint, Offset(size.width * 0.50f, size.height * 0.16f), Offset(size.width * 0.50f, size.height * 0.84f), strokeWidth, StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.16f, size.height * 0.50f), Offset(size.width * 0.84f, size.height * 0.50f), strokeWidth, StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.24f, size.height * 0.24f), Offset(size.width * 0.76f, size.height * 0.76f), strokeWidth, StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.76f, size.height * 0.24f), Offset(size.width * 0.24f, size.height * 0.76f), strokeWidth, StrokeCap.Round)
            }
            HistoryEventIcon.Motion -> {
                drawCircle(
                    color = tint,
                    radius = size.minDimension * 0.10f,
                    center = Offset(size.width * 0.50f, size.height * 0.50f)
                )
                drawArc(
                    color = tint,
                    startAngle = 40f,
                    sweepAngle = 80f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
                    size = Size(size.width * 0.64f, size.height * 0.64f),
                    style = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = tint,
                    startAngle = 160f,
                    sweepAngle = 80f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
                    size = Size(size.width * 0.64f, size.height * 0.64f),
                    style = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round)
                )
            }
            HistoryEventIcon.Node -> {
                val strokeWidth = 1.2.dp.toPx()
                drawCircle(tint, radius = size.minDimension * 0.10f, center = Offset(size.width * 0.28f, size.height * 0.32f))
                drawCircle(tint, radius = size.minDimension * 0.10f, center = Offset(size.width * 0.72f, size.height * 0.28f))
                drawCircle(tint, radius = size.minDimension * 0.10f, center = Offset(size.width * 0.52f, size.height * 0.72f))
                drawLine(tint, Offset(size.width * 0.35f, size.height * 0.36f), Offset(size.width * 0.64f, size.height * 0.32f), strokeWidth, StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.67f, size.height * 0.35f), Offset(size.width * 0.55f, size.height * 0.64f), strokeWidth, StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.34f, size.height * 0.38f), Offset(size.width * 0.48f, size.height * 0.64f), strokeWidth, StrokeCap.Round)
            }
            HistoryEventIcon.Warning -> {
                val triangle = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.10f)
                    lineTo(size.width * 0.90f, size.height * 0.84f)
                    lineTo(size.width * 0.10f, size.height * 0.84f)
                    close()
                }
                drawPath(triangle, tint)
                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.50f, size.height * 0.32f),
                    end = Offset(size.width * 0.50f, size.height * 0.58f),
                    strokeWidth = 1.5.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawCircle(
                    color = Color.White,
                    radius = size.minDimension * 0.05f,
                    center = Offset(size.width * 0.50f, size.height * 0.70f)
                )
            }
        }
    }
}

@Composable
private fun ChevronIcon(modifier: Modifier = Modifier, tint: Color = AlertHistoryChevron) {
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
private fun BottomNavIcon(type: BottomNavType, modifier: Modifier = Modifier, tint: Color = AlertHistoryNavy) {
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

private enum class HistoryEventIcon {
    Burst,
    Motion,
    Node,
    Warning
}

private data class EventBadge(
    val label: String,
    val background: Color,
    val textColor: Color
)

private val AlertHistoryBackgroundTop = Color(0xFF2732A0)
private val AlertHistoryBackgroundBottom = Color(0xFF222B8D)
private val AlertHistoryHeader = Color(0xFFC9CCEA)
private val AlertHistoryNavy = Color(0xFF11156D)
private val AlertHistoryBody = Color(0xFFAEB5EE)
private val AlertHistorySection = Color(0xFF8E97D8)
private val AlertHistoryDivider = Color(0xFF5560B4)
private val AlertHistoryEventsPill = Color(0xFF535CB2)
private val AlertHistoryEventsText = Color(0xFFC9CEFF)
private val AlertHistoryFilterBg = Color(0xFF4B54AF)
private val AlertHistoryFilterText = Color(0xFFE3E6FF)
private val AlertHistoryCardOne = Color(0xFF414CB1)
private val AlertHistoryCardTwo = Color(0xFF3E48A9)
private val AlertHistoryCardThree = Color(0xFF39439F)
private val AlertHistoryCardFour = Color(0xFF37409A)
private val AlertHistoryTime = Color(0xFF97A1E8)
private val AlertHistoryChevron = Color(0xFF94A0EA)
private val AlertHistorySoftPink = Color(0xFFFFD8D2)
private val AlertHistoryCritical = Color(0xFFE84A47)
private val AlertHistoryCriticalBadge = Color(0xFFEB5E58)
private val AlertHistoryMutedBadge = Color(0xFF5B63A9)
private val AlertHistoryMutedBadgeText = Color(0xFFD1D6FF)
private val AlertHistorySoftLavender = Color(0xFF525DCC)
private val AlertHistoryIconLavender = Color(0xFFDDE2FF)
private val AlertHistoryResolvedBadge = Color(0xFF74E57A)
private val AlertHistoryResolvedText = Color(0xFF0E6526)
private val AlertHistorySoftIndigo = Color(0xFF4C55B5)
private val AlertHistoryIconIndigo = Color(0xFFE0E5FF)
private val AlertHistoryAutoBadge = Color(0xFF5661B7)
private val AlertHistoryAutoText = Color(0xFFD4D8FF)
private val AlertHistorySoftWarning = Color(0xFF675DAA)
private val AlertHistoryWarning = Color(0xFFFFB46D)
private val AlertHistoryWarningBadge = Color(0xFFF08A3C)
private val AlertHistoryInsightCard = Color(0xFF2F3898)
private val AlertHistoryWaveStart = Color(0xFF4C56DF)
private val AlertHistoryWaveMid = Color(0xFF3753D6)
private val AlertHistoryWaveEnd = Color(0xFF1836B8)
private val AlertHistoryWaveStroke = Color(0xFF6EA1FF)
private val AlertHistoryInsightText = Color(0xFFB6C0FF)
private val AlertHistorySystemCard = Color(0xFF303A9A)
private val AlertHistorySystemIconBg = Color(0xFF2A348D)
private val AlertHistorySystemIcon = Color(0xFF8EF37D)
private val AlertHistorySystemText = Color(0xFFA8B0EA)
private val AlertHistoryNav = Color(0xFFD0D3EA)
private val AlertHistorySelectedPill = Color(0xFFE5EAFF)
private val AlertHistoryNavMuted = Color(0xFF8A90B6)
private val AlertHistoryAvatarBg = Color(0xFF496B57)
private val AlertHistoryAvatarFace = Color(0xFFF0BF8D)
private val AlertHistoryAvatarHair = Color(0xFF1D2A3D)
private val AlertHistoryAvatarAccent = Color(0xFF8BC56B)

private val HistoryTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 19.sp,
    lineHeight = 22.sp,
    fontWeight = FontWeight.ExtraBold
)

private val HistoryBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val HistoryCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 7.sp,
    lineHeight = 9.sp,
    fontWeight = FontWeight.Medium
)

@Preview(name = "Alert History", showBackground = true, backgroundColor = 0xFF2732A0)
@Composable
private fun AlertHistoryScreenPreview() {
    AmanAiTheme {
        AlertHistoryScreen()
    }
}
