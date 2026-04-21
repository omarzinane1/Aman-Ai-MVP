package com.amanai.features.fake_weather

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
fun WeatherForecastScreen(
    modifier: Modifier = Modifier,
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ForecastBackground)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp)
                .padding(top = 18.dp, bottom = 92.dp)
        ) {
            ForecastHeader()
            Spacer(modifier = Modifier.height(19.dp))
            CurrentWeatherCard()
            Spacer(modifier = Modifier.height(23.dp))
            SafetyStatusCard()
            Spacer(modifier = Modifier.height(24.dp))
            ForecastListSection()
            Spacer(modifier = Modifier.height(19.dp))
            WeatherDetailsGrid()
        }

        ForecastBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun ForecastHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(34.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CloudMark(modifier = Modifier.size(width = 16.dp, height = 11.dp))
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = "AMAN-AI",
            style = ForecastTinyLabel.copy(
                fontSize = 10.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Bold
            ),
            color = ForecastNavy
        )
        Spacer(modifier = Modifier.weight(1f))
        UserAvatar()
    }
}

@Composable
private fun CurrentWeatherCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(151.dp),
        shape = RoundedCornerShape(22.dp),
        color = ForecastCard,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 27.dp, vertical = 21.dp)
        ) {
            Text(
                text = "CURRENT LOCATION",
                style = ForecastTinyLabel,
                color = ForecastSoftText
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "San Francisco, CA",
                style = ForecastTitle,
                color = ForecastNavy
            )
            Spacer(modifier = Modifier.height(11.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "68\u00B0",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 48.sp,
                        letterSpacing = 0.sp
                    ),
                    color = ForecastNavy
                )
                Spacer(modifier = Modifier.width(25.dp))
                Column {
                    Text(
                        text = "Partly Cloudy",
                        style = ForecastBody.copy(fontSize = 13.sp, fontWeight = FontWeight.Medium),
                        color = ForecastText
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "H 72\u00B0 L 56\u00B0",
                        style = ForecastBody.copy(fontSize = 9.sp),
                        color = ForecastMuted
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row {
                WeatherMetaItem(
                    icon = WeatherIconType.Wind,
                    label = "WIND",
                    value = "12 mph"
                )
                Spacer(modifier = Modifier.width(42.dp))
                WeatherMetaItem(
                    icon = WeatherIconType.Humidity,
                    label = "HUMIDITY",
                    value = "64%"
                )
            }
        }
    }
}

@Composable
private fun WeatherMetaItem(
    icon: WeatherIconType,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherIcon(type = icon, modifier = Modifier.size(13.dp), tint = ForecastMuted)
        Spacer(modifier = Modifier.width(7.dp))
        Column {
            Text(
                text = label,
                style = ForecastTinyLabel.copy(fontSize = 7.sp, letterSpacing = 0.5.sp),
                color = ForecastSoftText
            )
            Text(
                text = value,
                style = ForecastBody.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold),
                color = ForecastNavy
            )
        }
    }
}

@Composable
private fun SafetyStatusCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(135.dp),
        shape = RoundedCornerShape(18.dp),
        color = ForecastNavy,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(top = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShieldIcon(modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = "Safety Status",
                style = ForecastBody.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 18.sp
                ),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "No weather alerts active for your current\narea. Travel is advised.",
                style = ForecastBody.copy(
                    fontSize = 9.sp,
                    lineHeight = 13.sp
                ),
                color = ForecastStatusText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(11.dp))
            Box(
                modifier = Modifier
                    .height(19.dp)
                    .background(ForecastSafePill, RoundedCornerShape(9.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "SECURE ZONE",
                    style = ForecastTinyLabel.copy(
                        fontSize = 8.sp,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun ForecastListSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "7-Day Forecast",
                style = ForecastBody.copy(fontSize = 13.sp, fontWeight = FontWeight.ExtraBold),
                color = ForecastNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "UPDATED 2M AGO",
                style = ForecastTinyLabel.copy(fontSize = 7.sp, letterSpacing = 0.5.sp),
                color = ForecastSoftText
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            color = Color.White,
            shadowElevation = 0.dp
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 13.dp)
            ) {
                ForecastDayRow("Monday", "Oct 23", WeatherIconType.Sun, "Sunny", "74\u00B0", "58\u00B0")
                ForecastDayRow("Tuesday", "Oct 24", WeatherIconType.Cloud, "Cloudy", "71\u00B0", "60\u00B0")
                ForecastDayRow("Wednesday", "Oct 25", WeatherIconType.Showers, "Showers", "65\u00B0", "55\u00B0")
                ForecastDayRow("Thursday", "Oct 26", WeatherIconType.PartlyCloudy, "Pt. Cloudy", "68\u00B0", "57\u00B0")
                ForecastDayRow("Friday", "Oct 27", WeatherIconType.Sun, "Sunny", "75\u00B0", "59\u00B0")
                ForecastDayRow("Saturday", "Oct 28", WeatherIconType.Sun, "Sunny", "78\u00B0", "62\u00B0")
                ForecastDayRow("Sunday", "Oct 29", WeatherIconType.Sun, "Sunny", "76\u00B0", "61\u00B0", isLast = true)
            }
        }
    }
}

@Composable
private fun ForecastDayRow(
    day: String,
    date: String,
    icon: WeatherIconType,
    condition: String,
    high: String,
    low: String,
    modifier: Modifier = Modifier,
    isLast: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(if (isLast) 34.dp else 38.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.width(86.dp)) {
            Text(
                text = day,
                style = ForecastBody.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold, lineHeight = 11.sp),
                color = ForecastText
            )
            Text(
                text = date,
                style = ForecastBody.copy(fontSize = 7.sp, lineHeight = 9.sp),
                color = ForecastSoftText
            )
        }
        WeatherIcon(
            type = icon,
            modifier = Modifier.size(14.dp),
            tint = when (icon) {
                WeatherIconType.Sun, WeatherIconType.PartlyCloudy -> ForecastSun
                WeatherIconType.Showers -> ForecastBlue
                else -> ForecastMuted
            }
        )
        Spacer(modifier = Modifier.width(11.dp))
        Text(
            text = condition,
            modifier = Modifier.width(64.dp),
            style = ForecastBody.copy(fontSize = 9.sp, fontWeight = FontWeight.Medium),
            color = ForecastMuted
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = high,
            style = ForecastBody.copy(fontSize = 11.sp, fontWeight = FontWeight.ExtraBold),
            color = ForecastNavy
        )
        Spacer(modifier = Modifier.width(21.dp))
        Text(
            text = low,
            style = ForecastBody.copy(fontSize = 11.sp),
            color = ForecastMuted
        )
    }
}

@Composable
private fun WeatherDetailsGrid(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            WeatherMetricCard(
                modifier = Modifier.weight(1f),
                icon = WeatherIconType.Uv,
                label = "UV INDEX",
                value = "Moderate"
            )
            WeatherMetricCard(
                modifier = Modifier.weight(1f),
                icon = WeatherIconType.Visibility,
                label = "VISIBILITY",
                value = "10 mi"
            )
        }
        Spacer(modifier = Modifier.height(13.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            WeatherMetricCard(
                modifier = Modifier.weight(1f),
                icon = WeatherIconType.Pressure,
                label = "PRESSURE",
                value = "29.92 in"
            )
            WeatherMetricCard(
                modifier = Modifier.weight(1f),
                icon = WeatherIconType.DewPoint,
                label = "DEW POINT",
                value = "54\u00B0"
            )
        }
    }
}

@Composable
private fun WeatherMetricCard(
    icon: WeatherIconType,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(58.dp),
        shape = RoundedCornerShape(17.dp),
        color = ForecastCard,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            WeatherIcon(type = icon, modifier = Modifier.size(13.dp), tint = ForecastSoftText)
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = label,
                style = ForecastTinyLabel.copy(fontSize = 7.sp, letterSpacing = 0.6.sp),
                color = ForecastSoftText
            )
            Text(
                text = value,
                style = ForecastBody.copy(fontSize = 11.sp, fontWeight = FontWeight.ExtraBold),
                color = ForecastNavy
            )
        }
    }
}

@Composable
private fun ForecastBottomBar(
    modifier: Modifier = Modifier,
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .navigationBarsPadding(),
        color = Color.White,
        shadowElevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp, vertical = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            BottomNavItem(WeatherIconType.Cloud, "Weather", false, onClick = onWeatherClick)
            BottomNavItem(WeatherIconType.Forecast, "Forecast", true, onClick = onForecastClick)
            BottomNavItem(WeatherIconType.Shield, "Safety", false, onClick = onSafetyClick)
            BottomNavItem(WeatherIconType.Settings, "Settings", false, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: WeatherIconType,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = if (selected) ForecastNavy else ForecastSoftText
    Column(
        modifier = modifier
            .width(47.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherIcon(type = icon, modifier = Modifier.size(15.dp), tint = color)
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = label,
            style = ForecastBody.copy(fontSize = 8.sp, fontWeight = FontWeight.Medium),
            color = color,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun UserAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(30.dp)) {
        drawCircle(color = ForecastNavy)
        drawCircle(
            color = Color.White.copy(alpha = 0.88f),
            radius = size.minDimension * 0.18f,
            center = Offset(size.width * 0.50f, size.height * 0.39f)
        )
        drawOval(
            color = Color.White.copy(alpha = 0.88f),
            topLeft = Offset(size.width * 0.26f, size.height * 0.57f),
            size = Size(size.width * 0.48f, size.height * 0.26f)
        )
        drawCircle(
            color = ForecastTeal,
            radius = size.minDimension * 0.11f,
            center = Offset(size.width * 0.68f, size.height * 0.30f)
        )
    }
}

@Composable
private fun CloudMark(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        val path = Path().apply {
            moveTo(size.width * 0.12f, size.height * 0.75f)
            quadraticBezierTo(size.width * 0.04f, size.height * 0.53f, size.width * 0.27f, size.height * 0.50f)
            quadraticBezierTo(size.width * 0.37f, size.height * 0.13f, size.width * 0.62f, size.height * 0.32f)
            quadraticBezierTo(size.width * 0.78f, size.height * 0.35f, size.width * 0.80f, size.height * 0.61f)
            quadraticBezierTo(size.width * 0.96f, size.height * 0.64f, size.width * 0.91f, size.height * 0.78f)
            close()
        }
        drawPath(path = path, color = ForecastNavy, style = stroke)
    }
}

@Composable
private fun ShieldIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.50f, size.height * 0.07f)
            lineTo(size.width * 0.78f, size.height * 0.18f)
            lineTo(size.width * 0.74f, size.height * 0.55f)
            quadraticBezierTo(size.width * 0.68f, size.height * 0.78f, size.width * 0.50f, size.height * 0.92f)
            quadraticBezierTo(size.width * 0.32f, size.height * 0.78f, size.width * 0.26f, size.height * 0.55f)
            lineTo(size.width * 0.22f, size.height * 0.18f)
            close()
        }
        drawPath(path = path, color = Color.White)
        drawCircle(
            color = ForecastNavy.copy(alpha = 0.2f),
            radius = size.minDimension * 0.12f,
            center = Offset(size.width * 0.50f, size.height * 0.45f)
        )
    }
}

@Composable
private fun WeatherIcon(
    type: WeatherIconType,
    modifier: Modifier = Modifier,
    tint: Color = ForecastNavy
) {
    Canvas(modifier = modifier) {
        when (type) {
            WeatherIconType.Sun -> {
                val radius = size.minDimension * 0.20f
                val center = Offset(size.width / 2f, size.height / 2f)
                drawCircle(color = tint, radius = radius, center = center)
                repeat(8) { index ->
                    val angle = Math.toRadians((index * 45).toDouble())
                    val start = Offset(
                        center.x + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.32f,
                        center.y + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.32f
                    )
                    val end = Offset(
                        center.x + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.45f,
                        center.y + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.45f
                    )
                    drawLine(color = tint, start = start, end = end, strokeWidth = 1.dp.toPx(), cap = StrokeCap.Round)
                }
            }
            WeatherIconType.Cloud, WeatherIconType.Forecast -> {
                drawCircle(color = tint.copy(alpha = 0.88f), radius = size.minDimension * 0.22f, center = Offset(size.width * 0.38f, size.height * 0.58f))
                drawCircle(color = tint.copy(alpha = 0.88f), radius = size.minDimension * 0.27f, center = Offset(size.width * 0.55f, size.height * 0.48f))
                drawOval(color = tint.copy(alpha = 0.88f), topLeft = Offset(size.width * 0.19f, size.height * 0.55f), size = Size(size.width * 0.63f, size.height * 0.25f))
                if (type == WeatherIconType.Forecast) {
                    drawLine(color = tint, start = Offset(size.width * 0.34f, size.height * 0.90f), end = Offset(size.width * 0.66f, size.height * 0.90f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                }
            }
            WeatherIconType.PartlyCloudy -> {
                drawCircle(color = ForecastSun, radius = size.minDimension * 0.22f, center = Offset(size.width * 0.38f, size.height * 0.40f))
                drawCircle(color = ForecastMuted, radius = size.minDimension * 0.20f, center = Offset(size.width * 0.46f, size.height * 0.61f))
                drawCircle(color = ForecastMuted, radius = size.minDimension * 0.24f, center = Offset(size.width * 0.62f, size.height * 0.55f))
                drawOval(color = ForecastMuted, topLeft = Offset(size.width * 0.33f, size.height * 0.61f), size = Size(size.width * 0.55f, size.height * 0.22f))
            }
            WeatherIconType.Showers -> {
                drawCircle(color = ForecastMuted, radius = size.minDimension * 0.21f, center = Offset(size.width * 0.40f, size.height * 0.42f))
                drawOval(color = ForecastMuted, topLeft = Offset(size.width * 0.18f, size.height * 0.40f), size = Size(size.width * 0.65f, size.height * 0.28f))
                repeat(3) { index ->
                    val x = size.width * (0.30f + index * 0.18f)
                    drawLine(color = tint, start = Offset(x, size.height * 0.75f), end = Offset(x - size.width * 0.06f, size.height * 0.96f), strokeWidth = 1.2.dp.toPx(), cap = StrokeCap.Round)
                }
            }
            WeatherIconType.Wind -> {
                drawLine(color = tint, start = Offset(size.width * 0.08f, size.height * 0.35f), end = Offset(size.width * 0.88f, size.height * 0.35f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
                drawLine(color = tint, start = Offset(size.width * 0.20f, size.height * 0.58f), end = Offset(size.width * 0.76f, size.height * 0.58f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
                drawLine(color = tint, start = Offset(size.width * 0.08f, size.height * 0.80f), end = Offset(size.width * 0.55f, size.height * 0.80f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
            }
            WeatherIconType.Humidity, WeatherIconType.DewPoint -> {
                val path = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.08f)
                    cubicTo(size.width * 0.78f, size.height * 0.38f, size.width * 0.86f, size.height * 0.57f, size.width * 0.70f, size.height * 0.78f)
                    quadraticBezierTo(size.width * 0.50f, size.height * 1.00f, size.width * 0.30f, size.height * 0.78f)
                    cubicTo(size.width * 0.14f, size.height * 0.57f, size.width * 0.22f, size.height * 0.38f, size.width * 0.50f, size.height * 0.08f)
                    close()
                }
                drawPath(path = path, color = tint)
            }
            WeatherIconType.Uv -> {
                drawCircle(color = tint, radius = size.minDimension * 0.17f, center = Offset(size.width / 2f, size.height / 2f))
                repeat(8) { index ->
                    val angle = Math.toRadians((index * 45).toDouble())
                    val start = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.34f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.34f)
                    val end = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.46f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.46f)
                    drawLine(color = tint, start = start, end = end, strokeWidth = 1.dp.toPx(), cap = StrokeCap.Round)
                }
            }
            WeatherIconType.Visibility -> {
                drawOval(color = tint, topLeft = Offset(size.width * 0.10f, size.height * 0.27f), size = Size(size.width * 0.80f, size.height * 0.46f), style = Stroke(width = 1.4.dp.toPx()))
                drawCircle(color = tint, radius = size.minDimension * 0.12f, center = Offset(size.width / 2f, size.height / 2f))
            }
            WeatherIconType.Pressure -> {
                drawCircle(color = tint, radius = size.minDimension * 0.36f, center = Offset(size.width / 2f, size.height / 2f), style = Stroke(width = 1.3.dp.toPx()))
                drawLine(color = tint, start = Offset(size.width / 2f, size.height / 2f), end = Offset(size.width * 0.70f, size.height * 0.35f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
            }
            WeatherIconType.Shield -> {
                val path = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.08f)
                    lineTo(size.width * 0.78f, size.height * 0.20f)
                    lineTo(size.width * 0.72f, size.height * 0.58f)
                    quadraticBezierTo(size.width * 0.66f, size.height * 0.79f, size.width * 0.50f, size.height * 0.93f)
                    quadraticBezierTo(size.width * 0.34f, size.height * 0.79f, size.width * 0.28f, size.height * 0.58f)
                    lineTo(size.width * 0.22f, size.height * 0.20f)
                    close()
                }
                drawPath(path = path, color = tint)
            }
            WeatherIconType.Settings -> {
                drawCircle(color = tint, radius = size.minDimension * 0.31f, center = Offset(size.width / 2f, size.height / 2f), style = Stroke(width = 1.4.dp.toPx()))
                drawCircle(color = tint, radius = size.minDimension * 0.10f, center = Offset(size.width / 2f, size.height / 2f))
                repeat(6) { index ->
                    val angle = Math.toRadians((index * 60).toDouble())
                    val start = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.39f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.39f)
                    val end = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.49f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.49f)
                    drawLine(color = tint, start = start, end = end, strokeWidth = 1.2.dp.toPx(), cap = StrokeCap.Round)
                }
            }
        }
    }
}

private enum class WeatherIconType {
    Sun,
    Cloud,
    PartlyCloudy,
    Showers,
    Wind,
    Humidity,
    Uv,
    Visibility,
    Pressure,
    DewPoint,
    Forecast,
    Shield,
    Settings
}

private val ForecastBackground = Color(0xFFFCFCFD)
private val ForecastCard = Color(0xFFF5F6F9)
private val ForecastNavy = Color(0xFF08056E)
private val ForecastText = Color(0xFF191A24)
private val ForecastMuted = Color(0xFF777985)
private val ForecastSoftText = Color(0xFFA6A7B0)
private val ForecastStatusText = Color(0xFFC7C8DE)
private val ForecastSafePill = Color(0xFF3438A6)
private val ForecastSun = Color(0xFFFFA20D)
private val ForecastBlue = Color(0xFF4769D8)
private val ForecastTeal = Color(0xFF66B8C5)

private val ForecastTitle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 17.sp,
    fontWeight = FontWeight.ExtraBold,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

private val ForecastBody = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 14.sp,
    letterSpacing = 0.sp
)

private val ForecastTinyLabel = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 8.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 10.sp,
    letterSpacing = 0.8.sp
)

@Preview(name = "Weather Forecast", showBackground = true, backgroundColor = 0xFFFCFCFD)
@Composable
private fun WeatherForecastScreenPreview() {
    AmanAiTheme {
        WeatherForecastScreen()
    }
}
