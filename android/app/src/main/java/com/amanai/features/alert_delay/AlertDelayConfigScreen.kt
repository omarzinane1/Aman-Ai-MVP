package com.amanai.features.alert_delay

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.unit.sp
import com.amanai.app.theme.AmanAiTheme

@Composable
fun AlertDelayConfigScreen(
    modifier: Modifier = Modifier,
    onSaveSettings: () -> Unit = {},
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
                        AlertBackgroundTop,
                        AlertBackgroundBottom
                    )
                )
            )
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp)
                .padding(top = 24.dp, bottom = 100.dp),
            horizontalAlignment = Alignment.Start
        ) {
            DelayIconBadge()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Alert Delay",
                style = AlertTitleStyle,
                color = AlertNavy
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Time to cancel an alert before it is sent to your\ncontacts.",
                style = AlertBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = AlertBodyText
            )
            Spacer(modifier = Modifier.height(24.dp))
            DelayPresetRow()
            Spacer(modifier = Modifier.height(18.dp))
            CustomDelayCard()
            Spacer(modifier = Modifier.height(20.dp))
            DelayInfoNotice()
            Spacer(modifier = Modifier.height(28.dp))
            SaveSettingsButton(onClick = onSaveSettings)
        }

        AlertBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun DelayIconBadge() {
    Surface(
        modifier = Modifier.size(34.dp),
        shape = CircleShape,
        color = AlertNavy,
        shadowElevation = 0.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            StopwatchIcon(
                modifier = Modifier.size(16.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun DelayPresetRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        DelayPresetChip(
            modifier = Modifier.weight(1f),
            seconds = "5s",
            label = "INSTANT",
            selected = false
        )
        DelayPresetChip(
            modifier = Modifier.weight(1f),
            seconds = "10s",
            label = "STANDARD",
            selected = true
        )
        DelayPresetChip(
            modifier = Modifier.weight(1f),
            seconds = "30s",
            label = "EXTENDED",
            selected = false
        )
    }
}

@Composable
private fun DelayPresetChip(
    modifier: Modifier = Modifier,
    seconds: String,
    label: String,
    selected: Boolean
) {
    Surface(
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(18.dp),
        color = if (selected) AlertNavy else AlertPresetCard,
        shadowElevation = if (selected) 8.dp else 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = seconds,
                style = AlertBodyStyle.copy(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                color = if (selected) Color.White else AlertNavy
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = label,
                style = AlertCapsStyle,
                color = if (selected) AlertSelectedSubtle else AlertMuted
            )
        }
    }
}

@Composable
private fun CustomDelayCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp),
        shape = RoundedCornerShape(22.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "CUSTOM DELAY",
                    style = AlertCapsStyle.copy(
                        fontSize = 8.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.1.sp
                    ),
                    color = AlertNavy
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "10",
                    style = AlertBodyStyle.copy(
                        fontSize = 34.sp,
                        lineHeight = 34.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = AlertNavy
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "sec",
                    style = AlertBodyStyle.copy(
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = AlertNavy
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            StaticDelaySlider(progress = 0.12f)
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "3 SEC",
                    style = AlertCapsStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 9.sp
                    ),
                    color = AlertMuted
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "60 SEC",
                    style = AlertCapsStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 9.sp
                    ),
                    color = AlertMuted
                )
            }
        }
    }
}

@Composable
private fun StaticDelaySlider(progress: Float) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        val knobSize = 14.dp
        val clamped = progress.coerceIn(0f, 1f)
        val knobOffset: Dp = (maxWidth - knobSize) * clamped

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(AlertSliderTrack, RoundedCornerShape(4.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(clamped)
                .height(4.dp)
                .background(AlertSliderActive, RoundedCornerShape(4.dp))
        )
        Box(
            modifier = Modifier
                .padding(start = knobOffset)
                .size(knobSize)
                .background(AlertNavy, CircleShape)
        )
    }
}

@Composable
private fun DelayInfoNotice() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(48.dp)
                .background(AlertNavy, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(12.dp))
        InfoIcon(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(12.dp),
            tint = AlertNavy
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Setting a longer delay gives you more time\nto prevent false alarms, while shorter delays\nensure faster emergency response.",
            style = AlertBodyStyle.copy(
                fontSize = 8.sp,
                lineHeight = 13.sp,
                fontWeight = FontWeight.Medium
            ),
            color = AlertInfoText
        )
    }
}

@Composable
private fun SaveSettingsButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(21.dp),
        color = AlertNavy,
        shadowElevation = 10.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "Save Settings",
                style = AlertBodyStyle.copy(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
    }
}

@Composable
private fun AlertBottomNav(
    modifier: Modifier = Modifier,
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(62.dp)
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
            AlertNavItem(type = AlertNavIcon.Weather, label = "Weather", selected = false, onClick = onWeatherClick)
            AlertNavItem(type = AlertNavIcon.Forecast, label = "Forecast", selected = false, onClick = onForecastClick)
            AlertNavItem(type = AlertNavIcon.Safety, label = "Safety", selected = false, onClick = onSafetyClick)
            AlertNavItem(type = AlertNavIcon.Settings, label = "Settings", selected = true, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun AlertNavItem(type: AlertNavIcon, label: String, selected: Boolean, onClick: () -> Unit) {
    val tint = if (selected) AlertNavy else AlertNavMuted
    Column(
        modifier = Modifier
            .width(44.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(29.dp)
                    .background(AlertSelectedPill, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                BottomNavIcon(type = type, modifier = Modifier.size(15.dp), tint = tint)
            }
            Spacer(modifier = Modifier.height(3.dp))
        } else {
            Spacer(modifier = Modifier.height(5.dp))
            BottomNavIcon(type = type, modifier = Modifier.size(15.dp), tint = tint)
            Spacer(modifier = Modifier.height(7.dp))
        }
        Text(
            text = label,
            style = AlertBodyStyle.copy(
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
private fun StopwatchIcon(
    modifier: Modifier = Modifier,
    tint: Color = AlertNavy
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.28f,
            center = Offset(size.width * 0.5f, size.height * 0.58f),
            style = stroke
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.5f, size.height * 0.14f),
            end = Offset(size.width * 0.5f, size.height * 0.28f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.41f, size.height * 0.15f),
            end = Offset(size.width * 0.59f, size.height * 0.15f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.5f, size.height * 0.58f),
            end = Offset(size.width * 0.5f, size.height * 0.45f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.5f, size.height * 0.58f),
            end = Offset(size.width * 0.61f, size.height * 0.50f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun InfoIcon(
    modifier: Modifier = Modifier,
    tint: Color = AlertNavy
) {
    Canvas(modifier = modifier) {
        drawCircle(color = tint)
        drawCircle(
            color = Color.White,
            radius = size.minDimension * 0.06f,
            center = Offset(size.width * 0.5f, size.height * 0.30f)
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.5f, size.height * 0.42f),
            end = Offset(size.width * 0.5f, size.height * 0.72f),
            strokeWidth = 1.4.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun BottomNavIcon(
    type: AlertNavIcon,
    modifier: Modifier = Modifier,
    tint: Color = AlertNavy
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            AlertNavIcon.Weather -> {
                drawCircle(tint, radius = size.minDimension * 0.18f, center = Offset(size.width * 0.38f, size.height * 0.58f))
                drawCircle(tint, radius = size.minDimension * 0.23f, center = Offset(size.width * 0.56f, size.height * 0.50f))
                drawOval(tint, topLeft = Offset(size.width * 0.18f, size.height * 0.56f), size = Size(size.width * 0.64f, size.height * 0.22f))
            }
            AlertNavIcon.Forecast -> {
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
            AlertNavIcon.Safety -> {
                val path = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.10f)
                    lineTo(size.width * 0.78f, size.height * 0.22f)
                    lineTo(size.width * 0.72f, size.height * 0.60f)
                    quadraticBezierTo(size.width * 0.66f, size.height * 0.80f, size.width * 0.50f, size.height * 0.92f)
                    quadraticBezierTo(size.width * 0.34f, size.height * 0.80f, size.width * 0.28f, size.height * 0.60f)
                    lineTo(size.width * 0.22f, size.height * 0.22f)
                    close()
                }
                drawPath(path = path, color = tint)
            }
            AlertNavIcon.Settings -> {
                drawCircle(tint, radius = size.minDimension * 0.30f, center = Offset(size.width / 2f, size.height / 2f), style = stroke)
                drawCircle(tint, radius = size.minDimension * 0.09f, center = Offset(size.width / 2f, size.height / 2f))
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

private enum class AlertNavIcon {
    Weather,
    Forecast,
    Safety,
    Settings
}

private val AlertBackgroundTop = Color(0xFFE9EAF5)
private val AlertBackgroundBottom = Color(0xFFFDFDFE)
private val AlertNavy = Color(0xFF111B7A)
private val AlertBodyText = Color(0xFF6E7287)
private val AlertPresetCard = Color(0xFFEAECF1)
private val AlertMuted = Color(0xFF8A8EA3)
private val AlertSelectedSubtle = Color(0xFFC4C8F8)
private val AlertSliderTrack = Color(0xFFE1E2E8)
private val AlertSliderActive = Color(0xFFCFD2E9)
private val AlertInfoText = Color(0xFF696C7D)
private val AlertSelectedPill = Color(0xFFE4E8FF)
private val AlertNavMuted = Color(0xFFA0A6C8)

private val AlertTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 18.sp,
    lineHeight = 22.sp,
    fontWeight = FontWeight.ExtraBold
)

private val AlertBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val AlertCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 7.sp,
    lineHeight = 9.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.9.sp
)

@Preview(name = "Alert Delay Config", showBackground = true, backgroundColor = 0xFFE9EAF5)
@Composable
private fun AlertDelayConfigScreenPreview() {
    AmanAiTheme {
        AlertDelayConfigScreen()
    }
}
