package com.amanai.features.permissions

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
fun PermissionsSetupScreen(
    modifier: Modifier = Modifier,
    onCompleteSetup: () -> Unit = {},
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
                        PermissionsBlueTop,
                        PermissionsBlueMid,
                        PermissionsBlueBottom
                    )
                )
            )
    ) {
        PermissionsHeader(
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
                .padding(bottom = 116.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "Access & Security",
                style = PermissionsTitleStyle,
                color = PermissionsLavender,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "To provide environmental sensing and\nemergency response, AMAN-AI requires\nspecific system authorizations.",
                style = PermissionsBodyStyle.copy(
                    fontSize = 10.sp,
                    lineHeight = 17.sp
                ),
                color = PermissionsBodyText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(18.dp))

            PermissionCard(
                icon = PermissionIconType.Microphone,
                title = "Microphone",
                description = "Allows the system to detect audio\ndistress signals and environmental\nhazards through sound analysis.",
                badgeText = "REQUIRED",
                badgeColor = PermissionsBadgeRequired
            ) {
                PermissionActionButton(text = "Grant Access")
            }

            Spacer(modifier = Modifier.height(12.dp))

            PermissionCard(
                icon = PermissionIconType.Activity,
                title = "Physical Activity",
                description = "Detects sudden falls or unusual\nmovement patterns to trigger safety\nprotocols automatically.",
                badgeText = "ENABLED",
                badgeColor = PermissionsBadgeEnabled
            ) {
                StatusFooter(text = "Activated")
            }

            Spacer(modifier = Modifier.height(12.dp))

            PermissionCard(
                icon = PermissionIconType.Location,
                title = "Location",
                description = "Essential for precise atmospheric\nweather forecasting and pinpointing\nhelp in emergencies.",
                badgeText = null,
                badgeColor = Color.Transparent
            ) {
                PermissionActionButton(text = "Set to \"Always\"")
            }

            Spacer(modifier = Modifier.height(12.dp))

            PermissionCard(
                icon = PermissionIconType.Notifications,
                title = "Notifications",
                description = "Real-time alerts for impending severe\nweather or critical security updates.",
                badgeText = null,
                badgeColor = Color.Transparent
            ) {
                ToggleFooter()
            }

            Spacer(modifier = Modifier.height(12.dp))

            PermissionCard(
                icon = PermissionIconType.Battery,
                title = "Battery Optimization",
                description = "Exempt AMAN-AI from battery\nrestrictions to ensure 24/7 background\nsentinel services.",
                badgeText = null,
                badgeColor = Color.Transparent
            ) {
                PermissionActionButton(text = "Settings")
            }

            Spacer(modifier = Modifier.height(20.dp))

            CompleteSetupButton(onClick = onCompleteSetup)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "YOUR DATA IS ENCRYPTED AND NEVER SHARED WITH\nTHIRD PARTIES.",
                style = PermissionsBodyStyle.copy(
                    fontSize = 7.sp,
                    lineHeight = 10.sp,
                    letterSpacing = 0.6.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = PermissionsLegal,
                textAlign = TextAlign.Center
            )
        }

        PermissionsBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun PermissionsHeader(modifier: Modifier = Modifier) {
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
            SmallCloudMark(modifier = Modifier.size(width = 15.dp, height = 10.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "AMAN-AI",
                style = PermissionsBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = PermissionsNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            PermissionAvatar()
        }
    }
}

@Composable
private fun PermissionCard(
    icon: PermissionIconType,
    title: String,
    description: String,
    badgeText: String?,
    badgeColor: Color,
    footer: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = PermissionsCard,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Surface(
                    modifier = Modifier.size(26.dp),
                    shape = CircleShape,
                    color = PermissionsIconCircle,
                    shadowElevation = 0.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        PermissionFeatureIcon(type = icon, modifier = Modifier.size(14.dp))
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = title,
                            style = PermissionsBodyStyle.copy(
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                        if (badgeText != null) {
                            Spacer(modifier = Modifier.weight(1f))
                            PermissionBadge(text = badgeText, color = badgeColor)
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = description,
                        style = PermissionsBodyStyle.copy(
                            fontSize = 8.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = PermissionsBodyText
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            footer()
        }
    }
}

@Composable
private fun PermissionBadge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .height(14.dp)
            .background(color = color, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = PermissionsBodyStyle.copy(
                fontSize = 6.sp,
                lineHeight = 7.sp,
                fontWeight = FontWeight.Bold
            ),
            color = if (text == "ENABLED") PermissionsBadgeEnabledText else Color.White
        )
    }
}

@Composable
private fun PermissionActionButton(text: String) {
    Box(
        modifier = Modifier
            .height(22.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = PermissionsBodyStyle.copy(
                fontSize = 8.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.Bold
            ),
            color = PermissionsNavy
        )
    }
}

@Composable
private fun StatusFooter(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(PermissionsGreen, CircleShape)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            style = PermissionsBodyStyle.copy(
                fontSize = 8.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.Bold
            ),
            color = PermissionsGreen
        )
    }
}

@Composable
private fun ToggleFooter() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Push alerts enabled",
            style = PermissionsBodyStyle.copy(
                fontSize = 8.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.Medium
            ),
            color = PermissionsBodyText
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(12.dp)
                .background(PermissionsToggleTrack, RoundedCornerShape(8.dp))
                .padding(horizontal = 1.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
                    .size(9.dp)
                    .background(Color.White, CircleShape)
            )
        }
    }
}

@Composable
private fun CompleteSetupButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(37.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = PermissionsNavy,
        shadowElevation = 0.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "Complete Setup",
                style = PermissionsBodyStyle.copy(
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
private fun PermissionsBottomNav(
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
            BottomNavItem(type = PermissionIconType.Weather, label = "Weather", selected = false, onClick = onWeatherClick)
            BottomNavItem(type = PermissionIconType.Forecast, label = "Forecast", selected = false, onClick = onForecastClick)
            BottomNavItem(type = PermissionIconType.Safety, label = "Safety", selected = true, onClick = onSafetyClick)
            BottomNavItem(type = PermissionIconType.Settings, label = "Settings", selected = false, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun BottomNavItem(
    type: PermissionIconType,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val tint = if (selected) PermissionsNavy else PermissionsNavMuted
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
                    .background(PermissionsSelectedPill, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                PermissionFeatureIcon(type = type, modifier = Modifier.size(15.dp), tint = tint)
            }
            Spacer(modifier = Modifier.height(3.dp))
        } else {
            Spacer(modifier = Modifier.height(5.dp))
            PermissionFeatureIcon(type = type, modifier = Modifier.size(15.dp), tint = tint)
            Spacer(modifier = Modifier.height(7.dp))
        }
        Text(
            text = label,
            style = PermissionsBodyStyle.copy(
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
private fun PermissionAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(20.dp)) {
        drawCircle(color = PermissionsNavy)
        drawCircle(
            color = PermissionsAvatarFace,
            radius = size.minDimension * 0.18f,
            center = Offset(size.width * 0.50f, size.height * 0.36f)
        )
        drawOval(
            color = PermissionsAvatarFace,
            topLeft = Offset(size.width * 0.30f, size.height * 0.52f),
            size = Size(size.width * 0.40f, size.height * 0.22f)
        )
        drawCircle(
            color = PermissionsGreen,
            radius = size.minDimension * 0.12f,
            center = Offset(size.width * 0.76f, size.height * 0.32f)
        )
    }
}

@Composable
private fun SmallCloudMark(modifier: Modifier = Modifier) {
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
        drawPath(path = path, color = PermissionsNavy, style = stroke)
    }
}

@Composable
private fun PermissionFeatureIcon(
    type: PermissionIconType,
    modifier: Modifier = Modifier,
    tint: Color = Color.White
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            PermissionIconType.Microphone -> {
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(size.width * 0.34f, size.height * 0.15f),
                    size = Size(size.width * 0.32f, size.height * 0.38f),
                    cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx()),
                    style = stroke
                )
                drawLine(tint, Offset(size.width * 0.50f, size.height * 0.53f), Offset(size.width * 0.50f, size.height * 0.78f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.34f, size.height * 0.78f), Offset(size.width * 0.66f, size.height * 0.78f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawArc(
                    color = tint,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.22f, size.height * 0.34f),
                    size = Size(size.width * 0.56f, size.height * 0.42f),
                    style = stroke
                )
            }
            PermissionIconType.Activity -> {
                drawLine(tint, Offset(size.width * 0.12f, size.height * 0.58f), Offset(size.width * 0.34f, size.height * 0.58f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.34f, size.height * 0.58f), Offset(size.width * 0.46f, size.height * 0.30f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.46f, size.height * 0.30f), Offset(size.width * 0.60f, size.height * 0.78f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.60f, size.height * 0.78f), Offset(size.width * 0.75f, size.height * 0.44f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.75f, size.height * 0.44f), Offset(size.width * 0.88f, size.height * 0.44f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
            }
            PermissionIconType.Location -> {
                val path = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.10f)
                    cubicTo(size.width * 0.73f, size.height * 0.10f, size.width * 0.88f, size.height * 0.29f, size.width * 0.88f, size.height * 0.49f)
                    cubicTo(size.width * 0.88f, size.height * 0.68f, size.width * 0.72f, size.height * 0.84f, size.width * 0.50f, size.height * 0.93f)
                    cubicTo(size.width * 0.28f, size.height * 0.84f, size.width * 0.12f, size.height * 0.68f, size.width * 0.12f, size.height * 0.49f)
                    cubicTo(size.width * 0.12f, size.height * 0.29f, size.width * 0.27f, size.height * 0.10f, size.width * 0.50f, size.height * 0.10f)
                    close()
                }
                drawPath(path = path, color = tint, style = stroke)
                drawCircle(tint, radius = size.minDimension * 0.11f, center = Offset(size.width * 0.50f, size.height * 0.47f), style = stroke)
            }
            PermissionIconType.Notifications -> {
                drawArc(
                    color = tint,
                    startAngle = 200f,
                    sweepAngle = 140f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.20f, size.height * 0.16f),
                    size = Size(size.width * 0.60f, size.height * 0.64f),
                    style = stroke
                )
                drawLine(tint, Offset(size.width * 0.24f, size.height * 0.63f), Offset(size.width * 0.76f, size.height * 0.63f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawCircle(tint, radius = size.minDimension * 0.07f, center = Offset(size.width * 0.50f, size.height * 0.83f))
            }
            PermissionIconType.Battery -> {
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.28f),
                    size = Size(size.width * 0.56f, size.height * 0.44f),
                    cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
                    style = stroke
                )
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(size.width * 0.75f, size.height * 0.40f),
                    size = Size(size.width * 0.08f, size.height * 0.18f),
                    cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx())
                )
                drawLine(tint, Offset(size.width * 0.48f, size.height * 0.32f), Offset(size.width * 0.37f, size.height * 0.54f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.37f, size.height * 0.54f), Offset(size.width * 0.50f, size.height * 0.54f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.50f, size.height * 0.54f), Offset(size.width * 0.42f, size.height * 0.78f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
            }
            PermissionIconType.Weather, PermissionIconType.Forecast -> {
                drawCircle(tint, radius = size.minDimension * 0.18f, center = Offset(size.width * 0.38f, size.height * 0.58f))
                drawCircle(tint, radius = size.minDimension * 0.23f, center = Offset(size.width * 0.56f, size.height * 0.50f))
                drawOval(tint, topLeft = Offset(size.width * 0.18f, size.height * 0.56f), size = Size(size.width * 0.64f, size.height * 0.22f))
                if (type == PermissionIconType.Forecast) {
                    drawLine(tint, Offset(size.width * 0.32f, size.height * 0.90f), Offset(size.width * 0.68f, size.height * 0.90f), strokeWidth = 1.4.dp.toPx(), cap = StrokeCap.Round)
                }
            }
            PermissionIconType.Safety -> {
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
            PermissionIconType.Settings -> {
                drawCircle(tint, radius = size.minDimension * 0.30f, center = Offset(size.width / 2f, size.height / 2f), style = stroke)
                drawCircle(tint, radius = size.minDimension * 0.09f, center = Offset(size.width / 2f, size.height / 2f))
                repeat(6) { index ->
                    val angle = Math.toRadians((index * 60).toDouble())
                    drawLine(
                        color = tint,
                        start = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.39f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.39f),
                        end = Offset(size.width / 2f + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.49f, size.height / 2f + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.49f),
                        strokeWidth = 1.2.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    }
}

private enum class PermissionIconType {
    Microphone,
    Activity,
    Location,
    Notifications,
    Battery,
    Weather,
    Forecast,
    Safety,
    Settings
}

private val PermissionsBlueTop = Color(0xFF09107C)
private val PermissionsBlueMid = Color(0xFF09126D)
private val PermissionsBlueBottom = Color(0xFF0B0E60)
private val PermissionsCard = Color(0xFF1C2184)
private val PermissionsIconCircle = Color(0xFF0D126D)
private val PermissionsLavender = Color(0xFFA8ACFF)
private val PermissionsBodyText = Color(0xFFA9ADD8)
private val PermissionsLegal = Color(0xFF666D9C)
private val PermissionsNavy = Color(0xFF11156D)
private val PermissionsBadgeRequired = Color(0xFFEB5C48)
private val PermissionsBadgeEnabled = Color(0xFF54E580)
private val PermissionsBadgeEnabledText = Color(0xFF0A5E23)
private val PermissionsGreen = Color(0xFF26D86B)
private val PermissionsToggleTrack = Color(0xFF48E36B)
private val PermissionsSelectedPill = Color(0xFFE5EAFF)
private val PermissionsNavMuted = Color(0xFFA0A6C8)
private val PermissionsAvatarFace = Color(0xFFE3B987)

private val PermissionsTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 29.sp,
    lineHeight = 33.sp,
    fontWeight = FontWeight.ExtraBold,
    letterSpacing = 0.sp
)

private val PermissionsBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp
)

@Preview(name = "Permissions Setup", showBackground = true, backgroundColor = 0xFF09107C)
@Composable
private fun PermissionsSetupScreenPreview() {
    AmanAiTheme {
        PermissionsSetupScreen()
    }
}
