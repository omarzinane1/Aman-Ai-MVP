package com.amanai.features.panic_quick_erase

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
fun PanicQuickEraseScreen(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PanicBackground)
    ) {
        PanicHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding(),
            onClose = onClose
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 72.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp)
                .padding(bottom = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Emergency\nControl Center",
                style = PanicTitleStyle,
                color = PanicNavy
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Management of sensitive data and app state.\nUse these tools only when immediate privacy\nis required.",
                style = PanicBodyStyle.copy(
                    fontSize = 9.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = PanicBody
            )
            Spacer(modifier = Modifier.height(18.dp))
            CriticalActionCard()
            Spacer(modifier = Modifier.height(14.dp))
            NuclearOptionCard()
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MiniActionCard(
                    modifier = Modifier.weight(1f),
                    icon = PanicMiniIcon.Stealth,
                    title = "Stealth Mode",
                    subtitle = "Hide safety traces"
                )
                MiniActionCard(
                    modifier = Modifier.weight(1f),
                    icon = PanicMiniIcon.Wipe,
                    title = "Wipe Logs",
                    subtitle = "Clear past 2 weeks"
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            SystemArmedCard()
            Spacer(modifier = Modifier.height(14.dp))
            ConfirmIdentityCard()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun PanicHeader(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {}
) {
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
                style = PanicBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = PanicNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clickable(onClick = onClose)
                    .background(PanicCloseBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                CloseIcon(
                    modifier = Modifier.size(10.dp),
                    tint = PanicCloseTint
                )
            }
        }
    }
}

@Composable
private fun CriticalActionCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = PanicWarningCard,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White.copy(alpha = 0.55f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                WarningIcon(
                    modifier = Modifier.size(12.dp),
                    tint = PanicWarning
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Critical Action",
                    style = PanicBodyStyle.copy(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = PanicWarning
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Executing the 'Nuclear Option' will\nimmediately purge all safety logs,\nemergency contacts, and private location\nhistory. The app will revert to a silent\nthreat state.",
                    style = PanicBodyStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = PanicBody
                )
            }
        }
    }
}

@Composable
private fun NuclearOptionCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp),
        shape = RoundedCornerShape(20.dp),
        color = PanicPrimaryBlue,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(Color.White.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                LightningIcon(
                    modifier = Modifier.size(14.dp),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Nuclear Option",
                style = PanicBodyStyle.copy(
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Clear All Safety Data",
                style = PanicBodyStyle.copy(
                    fontSize = 7.sp,
                    lineHeight = 9.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = PanicPrimarySubtext
            )
        }
    }
}

@Composable
private fun MiniActionCard(
    modifier: Modifier = Modifier,
    icon: PanicMiniIcon,
    title: String,
    subtitle: String
) {
    Surface(
        modifier = modifier.height(74.dp),
        shape = RoundedCornerShape(18.dp),
        color = PanicMiniCard,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 12.dp)
        ) {
            PanicMiniIconView(
                type = icon,
                modifier = Modifier.size(14.dp),
                tint = PanicNavy
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = PanicBodyStyle.copy(
                    fontSize = 9.sp,
                    lineHeight = 11.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = PanicNavy
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = PanicBodyStyle.copy(
                    fontSize = 6.sp,
                    lineHeight = 8.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = PanicMiniBody
            )
        }
    }
}

@Composable
private fun SystemArmedCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            PanicMonitorTop,
                            PanicMonitorBottom
                        )
                    )
                )
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawMonitorTexture()
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .background(PanicArmedDot, CircleShape)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "SYSTEM ARMED",
                    style = PanicCapsStyle.copy(
                        fontSize = 5.sp,
                        lineHeight = 7.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.8.sp
                    ),
                    color = PanicMonitorText
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "ENCRYPTION   AES-256",
                    style = PanicCapsStyle.copy(
                        fontSize = 4.sp,
                        lineHeight = 6.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.6.sp
                    ),
                    color = PanicMonitorText
                )
            }
        }
    }
}

private fun DrawScope.drawMonitorTexture() {
    drawLine(
        color = Color.White.copy(alpha = 0.10f),
        start = Offset(size.width * 0.00f, size.height * 0.18f),
        end = Offset(size.width, size.height * 0.18f),
        strokeWidth = 1.3.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = Color.White.copy(alpha = 0.08f),
        start = Offset(size.width * 0.00f, size.height * 0.42f),
        end = Offset(size.width, size.height * 0.42f),
        strokeWidth = 1.3.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = Color.White.copy(alpha = 0.10f),
        start = Offset(size.width * 0.12f, size.height * 0.04f),
        end = Offset(size.width * 0.74f, size.height * 0.82f),
        strokeWidth = 2.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = Color.White.copy(alpha = 0.08f),
        start = Offset(size.width * 0.28f, size.height * 0.02f),
        end = Offset(size.width * 0.88f, size.height * 0.88f),
        strokeWidth = 2.dp.toPx(),
        cap = StrokeCap.Round
    )
    val blurBand = Path().apply {
        moveTo(0f, size.height * 0.48f)
        cubicTo(
            size.width * 0.24f, size.height * 0.34f,
            size.width * 0.50f, size.height * 0.66f,
            size.width, size.height * 0.42f
        )
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }
    drawPath(
        path = blurBand,
        brush = Brush.verticalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.06f),
                Color.White.copy(alpha = 0.00f)
            )
        )
    )
}

@Composable
private fun ConfirmIdentityCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(146.dp),
        shape = RoundedCornerShape(20.dp),
        color = PanicPrimaryBlue,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Confirm Identity to Execute",
                style = PanicBodyStyle.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            PatternDots()
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "DRAW PATTERN TO CONFIRM",
                style = PanicCapsStyle.copy(
                    fontSize = 5.sp,
                    lineHeight = 7.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.9.sp
                ),
                color = PanicPrimarySubtext
            )
        }
    }
}

@Composable
private fun PatternDots() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(3) { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(3) { column ->
                    val active = row == 1 && column == 1
                    val medium = (row == 0 && column == 1) || (row == 1 && column == 0) || (row == 1 && column == 2) || (row == 2 && column == 1)
                    val size = when {
                        active -> 12.dp
                        medium -> 8.dp
                        else -> 6.dp
                    }
                    val color = when {
                        active -> Color.White.copy(alpha = 0.65f)
                        medium -> Color.White.copy(alpha = 0.35f)
                        else -> Color.White.copy(alpha = 0.22f)
                    }
                    Box(
                        modifier = Modifier
                            .size(size)
                            .background(color, CircleShape)
                    )
                }
            }
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
        drawPath(path = path, color = PanicNavy, style = stroke)
    }
}

@Composable
private fun CloseIcon(modifier: Modifier = Modifier, tint: Color = PanicCloseTint) {
    Canvas(modifier = modifier) {
        drawLine(
            color = tint,
            start = Offset(size.width * 0.22f, size.height * 0.22f),
            end = Offset(size.width * 0.78f, size.height * 0.78f),
            strokeWidth = 1.6.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.78f, size.height * 0.22f),
            end = Offset(size.width * 0.22f, size.height * 0.78f),
            strokeWidth = 1.6.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun WarningIcon(modifier: Modifier = Modifier, tint: Color = PanicWarning) {
    Canvas(modifier = modifier) {
        val triangle = Path().apply {
            moveTo(size.width * 0.50f, size.height * 0.12f)
            lineTo(size.width * 0.90f, size.height * 0.84f)
            lineTo(size.width * 0.10f, size.height * 0.84f)
            close()
        }
        drawPath(triangle, color = tint)
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.50f, size.height * 0.36f),
            end = Offset(size.width * 0.50f, size.height * 0.58f),
            strokeWidth = 1.4.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawCircle(
            color = Color.White,
            radius = size.minDimension * 0.05f,
            center = Offset(size.width * 0.50f, size.height * 0.70f)
        )
    }
}

@Composable
private fun LightningIcon(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier) {
        val bolt = Path().apply {
            moveTo(size.width * 0.56f, size.height * 0.08f)
            lineTo(size.width * 0.30f, size.height * 0.48f)
            lineTo(size.width * 0.48f, size.height * 0.48f)
            lineTo(size.width * 0.40f, size.height * 0.92f)
            lineTo(size.width * 0.72f, size.height * 0.42f)
            lineTo(size.width * 0.52f, size.height * 0.42f)
            close()
        }
        drawPath(bolt, color = tint)
    }
}

@Composable
private fun PanicMiniIconView(type: PanicMiniIcon, modifier: Modifier = Modifier, tint: Color = PanicNavy) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            PanicMiniIcon.Stealth -> {
                val eye = Path().apply {
                    moveTo(size.width * 0.10f, size.height * 0.50f)
                    quadraticBezierTo(size.width * 0.50f, size.height * 0.16f, size.width * 0.90f, size.height * 0.50f)
                    quadraticBezierTo(size.width * 0.50f, size.height * 0.84f, size.width * 0.10f, size.height * 0.50f)
                    close()
                }
                drawPath(eye, tint, style = stroke)
                drawCircle(tint, radius = size.minDimension * 0.10f, center = Offset(size.width * 0.50f, size.height * 0.50f))
                drawLine(tint, Offset(size.width * 0.12f, size.height * 0.18f), Offset(size.width * 0.88f, size.height * 0.82f), 1.3.dp.toPx(), StrokeCap.Round)
            }
            PanicMiniIcon.Wipe -> {
                drawArc(
                    color = tint,
                    startAngle = 130f,
                    sweepAngle = 250f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.14f, size.height * 0.14f),
                    size = Size(size.width * 0.72f, size.height * 0.72f),
                    style = stroke
                )
                drawLine(tint, Offset(size.width * 0.36f, size.height * 0.20f), Offset(size.width * 0.18f, size.height * 0.20f), 1.3.dp.toPx(), StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.18f, size.height * 0.20f), Offset(size.width * 0.18f, size.height * 0.38f), 1.3.dp.toPx(), StrokeCap.Round)
            }
        }
    }
}

private val PanicBackground = Color(0xFFFCFCFD)
private val PanicNavy = Color(0xFF11156D)
private val PanicBody = Color(0xFF6E7387)
private val PanicCloseBg = Color(0xFFF0F1F4)
private val PanicCloseTint = Color(0xFF767B8F)
private val PanicWarningCard = Color(0xFFF3E9E7)
private val PanicWarning = Color(0xFFF06739)
private val PanicPrimaryBlue = Color(0xFF27309A)
private val PanicPrimarySubtext = Color(0xFFAEB4F1)
private val PanicMiniCard = Color(0xFFF3F4F7)
private val PanicMiniBody = Color(0xFF858AA0)
private val PanicMonitorTop = Color(0xFFC6C6C8)
private val PanicMonitorBottom = Color(0xFFB4B5B8)
private val PanicArmedDot = Color(0xFF2CD35A)
private val PanicMonitorText = Color(0xFFF8F8F9)

private val PanicTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 24.sp,
    lineHeight = 26.sp,
    fontWeight = FontWeight.ExtraBold
)

private val PanicBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val PanicCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 7.sp,
    lineHeight = 9.sp,
    fontWeight = FontWeight.Medium
)

private enum class PanicMiniIcon {
    Stealth,
    Wipe
}

@Preview(name = "Panic Quick Erase", showBackground = true, backgroundColor = 0xFFFCFCFD)
@Composable
private fun PanicQuickEraseScreenPreview() {
    AmanAiTheme {
        PanicQuickEraseScreen()
    }
}
