package com.amanai.features.alert_detected

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
fun AlertDetectedScreen(
    modifier: Modifier = Modifier,
    onConfirmSos: () -> Unit = {},
    onCancelAlert: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AlertCriticalTop,
                        AlertCriticalBottom
                    )
                )
            )
    ) {
        CriticalTopBar(
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
                .padding(bottom = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            WarningCircle()
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "Potential Danger\nDetected",
                style = AlertCriticalTitleStyle,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Our AI sensors identified suspicious activity in\nyour immediate vicinity.",
                style = AlertCriticalBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 17.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = AlertCriticalBody,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            LocationStatusCard()
            Spacer(modifier = Modifier.height(22.dp))
            ConfirmSosButton(onClick = onConfirmSos)
            Spacer(modifier = Modifier.height(12.dp))
            CancelAlertButton(onClick = onCancelAlert)
            Spacer(modifier = Modifier.height(34.dp))
            MonitoringStrip()
        }
    }
}

@Composable
private fun CriticalTopBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp),
        color = AlertCriticalHeader,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CloudLogo(modifier = Modifier.size(width = 15.dp, height = 10.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "AMAN-AI",
                style = AlertCriticalBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            EmergencyAvatar()
        }
    }
}

@Composable
private fun WarningCircle() {
    Surface(
        modifier = Modifier.size(68.dp),
        shape = CircleShape,
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            WarningIcon(
                modifier = Modifier.size(26.dp),
                tint = AlertCriticalMain
            )
        }
    }
}

@Composable
private fun LocationStatusCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = AlertCriticalCard,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                text = "LOCATION STATUS",
                style = AlertCriticalCapsStyle.copy(
                    fontSize = 7.sp,
                    lineHeight = 9.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.1.sp
                ),
                color = AlertCriticalLabel
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                PinIcon(
                    modifier = Modifier.size(12.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = "Downtown Sector, Block 4B",
                    style = AlertCriticalBodyStyle.copy(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun ConfirmSosButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SOS",
                style = AlertCriticalBodyStyle.copy(
                    fontSize = 13.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                color = AlertCriticalMain
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "CONFIRM SOS",
                style = AlertCriticalBodyStyle.copy(
                    fontSize = 13.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = AlertCriticalMain
            )
        }
    }
}

@Composable
private fun CancelAlertButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(21.dp),
        color = Color.Transparent,
        border = StrokeBorder,
        shadowElevation = 0.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "I am safe, Cancel alert",
                style = AlertCriticalBodyStyle.copy(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
    }
}

private val StrokeBorder = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.72f))

@Composable
private fun MonitoringStrip() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(2.dp),
        color = AlertCriticalMonitorBg,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 3.dp)
                    .size(5.dp)
                    .background(AlertCriticalGreen, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Secure link to dispatch established. Monitoring\nyour vitals...",
                style = AlertCriticalBodyStyle.copy(
                    fontSize = 8.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Color.White
            )
        }
    }
}

@Composable
private fun EmergencyAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(22.dp)) {
        drawCircle(color = AlertCriticalAvatarBg)
        drawCircle(
            color = AlertCriticalAvatarFace,
            radius = size.minDimension * 0.17f,
            center = Offset(size.width * 0.50f, size.height * 0.35f)
        )
        drawOval(
            color = AlertCriticalAvatarFace,
            topLeft = Offset(size.width * 0.32f, size.height * 0.52f),
            size = Size(size.width * 0.36f, size.height * 0.20f)
        )
        drawArc(
            color = AlertCriticalAvatarHair,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width * 0.26f, size.height * 0.10f),
            size = Size(size.width * 0.48f, size.height * 0.32f)
        )
        drawRect(
            color = AlertCriticalAvatarAccent,
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
        drawPath(path = path, color = Color.White, style = stroke)
    }
}

@Composable
private fun WarningIcon(
    modifier: Modifier = Modifier,
    tint: Color = AlertCriticalMain
) {
    Canvas(modifier = modifier) {
        val triangle = Path().apply {
            moveTo(size.width * 0.50f, size.height * 0.08f)
            lineTo(size.width * 0.92f, size.height * 0.88f)
            lineTo(size.width * 0.08f, size.height * 0.88f)
            close()
        }
        drawPath(path = triangle, color = tint)
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.50f, size.height * 0.32f),
            end = Offset(size.width * 0.50f, size.height * 0.58f),
            strokeWidth = 2.2.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawCircle(
            color = Color.White,
            radius = size.minDimension * 0.05f,
            center = Offset(size.width * 0.50f, size.height * 0.72f)
        )
    }
}

@Composable
private fun PinIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.White
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
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
            radius = size.minDimension * 0.08f,
            center = Offset(size.width * 0.50f, size.height * 0.43f)
        )
    }
}

private val AlertCriticalTop = Color(0xFFD11F1F)
private val AlertCriticalBottom = Color(0xFFC91D1D)
private val AlertCriticalHeader = Color(0xFFCD1A1A)
private val AlertCriticalMain = Color(0xFFD11F1F)
private val AlertCriticalBody = Color(0xFFF4D2D2)
private val AlertCriticalCard = Color(0xFFD85555)
private val AlertCriticalLabel = Color(0xFFFFE0E0)
private val AlertCriticalMonitorBg = Color(0xFFAE1717)
private val AlertCriticalGreen = Color(0xFF86F56F)
private val AlertCriticalAvatarBg = Color(0xFF525B61)
private val AlertCriticalAvatarFace = Color(0xFFF0BF8D)
private val AlertCriticalAvatarHair = Color(0xFF1D2A3D)
private val AlertCriticalAvatarAccent = Color(0xFFF0C657)

private val AlertCriticalTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 24.sp,
    lineHeight = 28.sp,
    fontWeight = FontWeight.ExtraBold
)

private val AlertCriticalBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val AlertCriticalCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 7.sp,
    lineHeight = 9.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.9.sp
)

@Preview(name = "Alert Detected", showBackground = true, backgroundColor = 0xFFD11F1F)
@Composable
private fun AlertDetectedScreenPreview() {
    AmanAiTheme {
        AlertDetectedScreen()
    }
}
