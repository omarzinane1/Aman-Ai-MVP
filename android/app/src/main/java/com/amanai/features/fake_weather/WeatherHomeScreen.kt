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
fun WeatherHomeScreen(
    modifier: Modifier = Modifier,
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(HomeBackground)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 23.dp, bottom = 89.dp)
        ) {
            WeatherHomeHeader()
            Spacer(modifier = Modifier.height(24.dp))
            CurrentConditionsHero()
            Spacer(modifier = Modifier.height(23.dp))
            HourlyForecastSection()
            Spacer(modifier = Modifier.height(19.dp))
            HomeMetricRow()
            Spacer(modifier = Modifier.height(13.dp))
            PrecipitationCard()
            Spacer(modifier = Modifier.height(13.dp))
            AirQualityCard()
        }

        WeatherHomeBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun WeatherHomeHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeCloudLogo(modifier = Modifier.size(width = 17.dp, height = 12.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "AMAN-AI",
            style = HomeBody.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 17.sp
            ),
            color = HomeNavy
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "LOCATION",
                style = HomeTiny.copy(
                    fontSize = 7.sp,
                    letterSpacing = 1.7.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = HomeSoftText
            )
            Text(
                text = "London",
                style = HomeBody.copy(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 10.sp
                ),
                color = HomeNavy
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        HomeAvatar()
    }
}

@Composable
private fun CurrentConditionsHero(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(173.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        PartlyCloudyIcon(modifier = Modifier.size(width = 63.dp, height = 45.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "22\u00B0C",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 53.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 56.sp,
                letterSpacing = 0.sp
            ),
            color = HomeNavy
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "Partly Cloudy",
            style = HomeBody.copy(fontSize = 21.sp, lineHeight = 24.sp),
            color = HomeText
        )
        Spacer(modifier = Modifier.height(22.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            MiniWeatherStat(icon = HomeIconType.Humidity, text = "42% Humidity")
            Spacer(modifier = Modifier.width(21.dp))
            MiniWeatherStat(icon = HomeIconType.Wind, text = "12 km/h Wind")
        }
    }
}

@Composable
private fun MiniWeatherStat(
    icon: HomeIconType,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        HomeIcon(type = icon, modifier = Modifier.size(11.dp), tint = HomeMuted)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = HomeBody.copy(fontSize = 9.sp, lineHeight = 11.sp),
            color = HomeMuted
        )
    }
}

@Composable
private fun HourlyForecastSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Hourly Forecast",
                style = HomeBody.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 17.sp
                ),
                color = HomeText
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Next 24h",
                style = HomeBody.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 12.sp
                ),
                color = HomeNavy
            )
        }
        Spacer(modifier = Modifier.height(13.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HourlyWeatherCard(
                modifier = Modifier.weight(1f),
                time = "Now",
                temperature = "22\u00B0",
                icon = HomeIconType.Cloud,
                selected = false
            )
            HourlyWeatherCard(
                modifier = Modifier.weight(1f),
                time = "14:00",
                temperature = "24\u00B0",
                icon = HomeIconType.Sun,
                selected = true
            )
            HourlyWeatherCard(
                modifier = Modifier.weight(1f),
                time = "15:00",
                temperature = "25\u00B0",
                icon = HomeIconType.Sun,
                selected = false
            )
            HourlyWeatherCard(
                modifier = Modifier.weight(1f),
                time = "16:00",
                temperature = "23\u00B0",
                icon = HomeIconType.PartlyCloudy,
                selected = false
            )
        }
    }
}

@Composable
private fun HourlyWeatherCard(
    time: String,
    temperature: String,
    icon: HomeIconType,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val background = if (selected) HomeNavy else HomeCard
    val primary = if (selected) Color.White else HomeText
    val secondary = if (selected) Color.White.copy(alpha = 0.72f) else HomeMuted
    Surface(
        modifier = modifier.height(76.dp),
        shape = RoundedCornerShape(19.dp),
        color = background,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(top = 10.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time,
                style = HomeBody.copy(fontSize = 8.sp, lineHeight = 10.sp),
                color = secondary
            )
            Spacer(modifier = Modifier.height(9.dp))
            HomeIcon(
                type = icon,
                modifier = Modifier.size(18.dp),
                tint = if (selected) Color.White else HomeNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = temperature,
                style = HomeBody.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 12.sp
                ),
                color = primary
            )
        }
    }
}

@Composable
private fun HomeMetricRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        HomeMetricCard(
            modifier = Modifier.weight(1f),
            icon = HomeIconType.Uv,
            label = "UV INDEX",
            value = "4 Moderate",
            bottomLine = true
        )
        HomeMetricCard(
            modifier = Modifier.weight(1f),
            icon = HomeIconType.Sunrise,
            label = "SUNRISE",
            value = "06:12 AM",
            helper = "Sunset: 08:04 PM"
        )
    }
}

@Composable
private fun HomeMetricCard(
    icon: HomeIconType,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    helper: String? = null,
    bottomLine: Boolean = false
) {
    Surface(
        modifier = modifier.height(91.dp),
        shape = RoundedCornerShape(19.dp),
        color = HomeCard,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HomeIcon(type = icon, modifier = Modifier.size(11.dp), tint = HomeMuted)
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = label,
                    style = HomeTiny,
                    color = HomeMuted
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = HomeBody.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 20.sp
                ),
                color = HomeText
            )
            Spacer(modifier = Modifier.weight(1f))
            if (bottomLine) {
                Box(
                    modifier = Modifier
                        .width(55.dp)
                        .height(3.dp)
                        .background(HomeNavy, RoundedCornerShape(2.dp))
                )
            } else if (helper != null) {
                Text(
                    text = helper,
                    style = HomeBody.copy(fontSize = 8.sp, lineHeight = 10.sp),
                    color = HomeMuted
                )
            }
        }
    }
}

@Composable
private fun PrecipitationCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(82.dp),
        shape = RoundedCornerShape(19.dp),
        color = HomeCard,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HomeIcon(type = HomeIconType.Precipitation, modifier = Modifier.size(11.dp), tint = HomeMuted)
                    Spacer(modifier = Modifier.width(7.dp))
                    Text("PRECIPITATION", style = HomeTiny, color = HomeMuted)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "0% in last 24h",
                    style = HomeBody.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 21.sp
                    ),
                    color = HomeText
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Next expected: Tuesday morning",
                    style = HomeBody.copy(fontSize = 9.sp, lineHeight = 11.sp),
                    color = HomeMuted
                )
            }
            PercentageRing()
        }
    }
}

@Composable
private fun PercentageRing(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(66.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = HomeRing,
                radius = size.minDimension * 0.42f,
                style = Stroke(width = 3.dp.toPx())
            )
        }
        Text(
            text = "0%",
            style = HomeBody.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp
            ),
            color = HomeNavy
        )
    }
}

@Composable
private fun AirQualityCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(94.dp),
        shape = RoundedCornerShape(19.dp),
        color = HomeAirCard,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HomeIcon(type = HomeIconType.Air, modifier = Modifier.size(11.dp), tint = HomeMuted)
                    Spacer(modifier = Modifier.width(7.dp))
                    Text("AIR QUALITY", style = HomeTiny, color = HomeMuted)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "32 - Excellent",
                    style = HomeBody.copy(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 20.sp
                    ),
                    color = HomeText
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Air quality is ideal for most individuals;\nenjoy your normal outdoor activities.",
                    style = HomeBody.copy(fontSize = 9.sp, lineHeight = 12.sp),
                    color = HomeText
                )
            }
            SmileStatusIcon(modifier = Modifier.size(34.dp))
        }
    }
}

@Composable
private fun WeatherHomeBottomBar(
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
                .padding(horizontal = 27.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            HomeBottomItem(HomeIconType.Cloud, "Weather", selected = true, onClick = onWeatherClick)
            HomeBottomItem(HomeIconType.Forecast, "Forecast", selected = false, onClick = onForecastClick)
            HomeBottomItem(HomeIconType.Shield, "Safety", selected = false, onClick = onSafetyClick)
            HomeBottomItem(HomeIconType.Settings, "Settings", selected = false, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun HomeBottomItem(
    icon: HomeIconType,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if (selected) HomeNavy else HomeSoftText
    Column(
        modifier = modifier
            .width(49.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
                    .background(HomeSelectedTab, RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                HomeIcon(type = icon, modifier = Modifier.size(17.dp), tint = contentColor)
            }
            Spacer(modifier = Modifier.height(3.dp))
        } else {
            Spacer(modifier = Modifier.height(6.dp))
            HomeIcon(type = icon, modifier = Modifier.size(16.dp), tint = contentColor)
            Spacer(modifier = Modifier.height(7.dp))
        }
        Text(
            text = label,
            style = HomeBody.copy(
                fontSize = 8.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 10.sp
            ),
            color = contentColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun HomeAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(30.dp)) {
        drawCircle(color = HomeNavy)
        drawCircle(
            color = HomeWarm,
            radius = size.minDimension * 0.23f,
            center = Offset(size.width * 0.50f, size.height * 0.38f)
        )
        drawOval(
            color = HomeWarm,
            topLeft = Offset(size.width * 0.25f, size.height * 0.57f),
            size = Size(size.width * 0.50f, size.height * 0.27f)
        )
    }
}

@Composable
private fun SmileStatusIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 2.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        val center = Offset(size.width / 2f, size.height / 2f)
        drawCircle(color = HomeGreen, radius = size.minDimension * 0.34f, center = center, style = stroke)
        drawCircle(color = HomeGreen, radius = size.minDimension * 0.035f, center = Offset(size.width * 0.40f, size.height * 0.45f))
        drawCircle(color = HomeGreen, radius = size.minDimension * 0.035f, center = Offset(size.width * 0.60f, size.height * 0.45f))
        drawArc(
            color = HomeGreen,
            startAngle = 20f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(size.width * 0.36f, size.height * 0.43f),
            size = Size(size.width * 0.28f, size.height * 0.28f),
            style = stroke
        )
    }
}

@Composable
private fun PartlyCloudyIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val center = Offset(size.width * 0.53f, size.height * 0.35f)
        drawCircle(color = HomeNavy, radius = size.minDimension * 0.24f, center = center)
        repeat(8) { index ->
            val angle = Math.toRadians((index * 45).toDouble())
            val start = Offset(
                center.x + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.34f,
                center.y + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.34f
            )
            val end = Offset(
                center.x + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.48f,
                center.y + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.48f
            )
            drawLine(color = HomeNavy, start = start, end = end, strokeWidth = 3.dp.toPx(), cap = StrokeCap.Round)
        }
        drawCircle(color = HomeNavy, radius = size.minDimension * 0.22f, center = Offset(size.width * 0.35f, size.height * 0.60f))
        drawCircle(color = HomeNavy, radius = size.minDimension * 0.28f, center = Offset(size.width * 0.53f, size.height * 0.53f))
        drawRoundRect(
            color = HomeNavy,
            topLeft = Offset(size.width * 0.20f, size.height * 0.58f),
            size = Size(size.width * 0.58f, size.height * 0.24f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(20.dp.toPx(), 20.dp.toPx())
        )
    }
}

@Composable
private fun HomeCloudLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        val path = Path().apply {
            moveTo(size.width * 0.12f, size.height * 0.76f)
            quadraticBezierTo(size.width * 0.02f, size.height * 0.55f, size.width * 0.28f, size.height * 0.50f)
            quadraticBezierTo(size.width * 0.38f, size.height * 0.12f, size.width * 0.63f, size.height * 0.32f)
            quadraticBezierTo(size.width * 0.80f, size.height * 0.36f, size.width * 0.82f, size.height * 0.62f)
            quadraticBezierTo(size.width * 0.97f, size.height * 0.65f, size.width * 0.91f, size.height * 0.79f)
            close()
        }
        drawPath(path = path, color = HomeNavy, style = stroke)
    }
}

@Composable
private fun HomeIcon(
    type: HomeIconType,
    modifier: Modifier = Modifier,
    tint: Color = HomeNavy
) {
    Canvas(modifier = modifier) {
        when (type) {
            HomeIconType.Sun -> {
                val center = Offset(size.width / 2f, size.height / 2f)
                drawCircle(color = tint, radius = size.minDimension * 0.18f, center = center)
                repeat(8) { index ->
                    val angle = Math.toRadians((index * 45).toDouble())
                    val start = Offset(
                        center.x + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.31f,
                        center.y + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.31f
                    )
                    val end = Offset(
                        center.x + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.45f,
                        center.y + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.45f
                    )
                    drawLine(color = tint, start = start, end = end, strokeWidth = 1.2.dp.toPx(), cap = StrokeCap.Round)
                }
            }
            HomeIconType.Cloud, HomeIconType.Forecast -> {
                drawCircle(color = tint, radius = size.minDimension * 0.22f, center = Offset(size.width * 0.38f, size.height * 0.58f))
                drawCircle(color = tint, radius = size.minDimension * 0.27f, center = Offset(size.width * 0.55f, size.height * 0.49f))
                drawOval(color = tint, topLeft = Offset(size.width * 0.19f, size.height * 0.55f), size = Size(size.width * 0.63f, size.height * 0.25f))
                if (type == HomeIconType.Forecast) {
                    drawLine(color = tint, start = Offset(size.width * 0.32f, size.height * 0.91f), end = Offset(size.width * 0.68f, size.height * 0.91f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                }
            }
            HomeIconType.PartlyCloudy -> {
                drawCircle(color = tint, radius = size.minDimension * 0.18f, center = Offset(size.width * 0.38f, size.height * 0.38f))
                drawCircle(color = tint, radius = size.minDimension * 0.19f, center = Offset(size.width * 0.45f, size.height * 0.62f))
                drawCircle(color = tint, radius = size.minDimension * 0.23f, center = Offset(size.width * 0.62f, size.height * 0.55f))
                drawOval(color = tint, topLeft = Offset(size.width * 0.32f, size.height * 0.61f), size = Size(size.width * 0.56f, size.height * 0.22f))
            }
            HomeIconType.Humidity, HomeIconType.Precipitation -> {
                val path = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.08f)
                    cubicTo(size.width * 0.78f, size.height * 0.38f, size.width * 0.86f, size.height * 0.57f, size.width * 0.70f, size.height * 0.78f)
                    quadraticBezierTo(size.width * 0.50f, size.height * 0.99f, size.width * 0.30f, size.height * 0.78f)
                    cubicTo(size.width * 0.14f, size.height * 0.57f, size.width * 0.22f, size.height * 0.38f, size.width * 0.50f, size.height * 0.08f)
                    close()
                }
                drawPath(path = path, color = tint)
            }
            HomeIconType.Wind -> {
                drawLine(color = tint, start = Offset(size.width * 0.08f, size.height * 0.34f), end = Offset(size.width * 0.88f, size.height * 0.34f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
                drawLine(color = tint, start = Offset(size.width * 0.21f, size.height * 0.57f), end = Offset(size.width * 0.76f, size.height * 0.57f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
                drawLine(color = tint, start = Offset(size.width * 0.08f, size.height * 0.79f), end = Offset(size.width * 0.56f, size.height * 0.79f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
            }
            HomeIconType.Uv -> {
                drawCircle(color = tint, radius = size.minDimension * 0.17f, center = Offset(size.width / 2f, size.height / 2f))
                repeat(8) { index ->
                    val angle = Math.toRadians((index * 45).toDouble())
                    drawLine(
                        color = tint,
                        start = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.33f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.33f),
                        end = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.47f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.47f),
                        strokeWidth = 1.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
            HomeIconType.Sunrise -> {
                drawArc(
                    color = tint,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.20f),
                    size = Size(size.width * 0.64f, size.height * 0.64f),
                    style = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round)
                )
                drawLine(color = tint, start = Offset(size.width * 0.14f, size.height * 0.72f), end = Offset(size.width * 0.86f, size.height * 0.72f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
                drawLine(color = tint, start = Offset(size.width * 0.50f, size.height * 0.23f), end = Offset(size.width * 0.50f, size.height * 0.50f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
            }
            HomeIconType.Air -> {
                drawLine(color = tint, start = Offset(size.width * 0.12f, size.height * 0.35f), end = Offset(size.width * 0.86f, size.height * 0.35f), strokeWidth = 1.1.dp.toPx(), cap = StrokeCap.Round)
                drawLine(color = tint, start = Offset(size.width * 0.21f, size.height * 0.56f), end = Offset(size.width * 0.74f, size.height * 0.56f), strokeWidth = 1.1.dp.toPx(), cap = StrokeCap.Round)
                drawLine(color = tint, start = Offset(size.width * 0.31f, size.height * 0.77f), end = Offset(size.width * 0.62f, size.height * 0.77f), strokeWidth = 1.1.dp.toPx(), cap = StrokeCap.Round)
            }
            HomeIconType.Shield -> {
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
            HomeIconType.Settings -> {
                drawCircle(color = tint, radius = size.minDimension * 0.31f, center = Offset(size.width / 2f, size.height / 2f), style = Stroke(width = 1.3.dp.toPx()))
                drawCircle(color = tint, radius = size.minDimension * 0.10f, center = Offset(size.width / 2f, size.height / 2f))
                repeat(6) { index ->
                    val angle = Math.toRadians((index * 60).toDouble())
                    drawLine(
                        color = tint,
                        start = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.40f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.40f),
                        end = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.49f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.49f),
                        strokeWidth = 1.1.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    }
}

private enum class HomeIconType {
    Sun,
    Cloud,
    PartlyCloudy,
    Humidity,
    Wind,
    Uv,
    Sunrise,
    Precipitation,
    Air,
    Forecast,
    Shield,
    Settings
}

private val HomeBackground = Color(0xFFFCFCFD)
private val HomeCard = Color(0xFFF5F6F9)
private val HomeAirCard = Color(0xFFF8FAF7)
private val HomeSelectedTab = Color(0xFFE8ECFF)
private val HomeNavy = Color(0xFF08056E)
private val HomeText = Color(0xFF171821)
private val HomeMuted = Color(0xFF858894)
private val HomeSoftText = Color(0xFFA9AAB3)
private val HomeRing = Color(0xFFD9DCE8)
private val HomeGreen = Color(0xFF0C8C43)
private val HomeWarm = Color(0xFFE7B884)

private val HomeBody = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 15.sp,
    letterSpacing = 0.sp
)

private val HomeTiny = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 7.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 9.sp,
    letterSpacing = 1.1.sp
)

@Preview(name = "Weather Home", showBackground = true, backgroundColor = 0xFFFCFCFD)
@Composable
private fun WeatherHomeScreenPreview() {
    AmanAiTheme {
        WeatherHomeScreen()
    }
}
