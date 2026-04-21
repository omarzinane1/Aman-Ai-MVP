package com.amanai.features.secret_access

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
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
fun SecretAccessPinScreen(
    modifier: Modifier = Modifier,
    onPinValidated: () -> Unit = {}
) {
    var pinLength by remember { mutableIntStateOf(0) }

    LaunchedEffect(pinLength) {
        if (pinLength >= 4) {
            onPinValidated()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SecretBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        SecretHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 25.dp, vertical = 25.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(3.dp)
                    .background(SecretHandle, RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.height(44.dp))
            Text(
                text = "VERIFICATION REQUIRED",
                style = SecretSerif.copy(
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 3.4.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SecretMuted,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(27.dp))
            PinProgressDots(filled = pinLength.coerceIn(0, 4))
            Spacer(modifier = Modifier.height(38.dp))
            PinKeypad(
                onDigitPress = {
                    if (pinLength < 4) {
                        pinLength += 1
                    }
                },
                onBackspace = {
                    if (pinLength > 0) {
                        pinLength -= 1
                    }
                },
                onBiometricPress = onPinValidated
            )
        }

        Text(
            text = "CANCEL",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 19.dp),
            style = SecretSerif.copy(
                fontSize = 11.sp,
                lineHeight = 13.sp,
                letterSpacing = 1.5.sp,
                fontWeight = FontWeight.Bold
            ),
            color = SecretMuted,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SecretHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(34.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SecretCloudLogo(modifier = Modifier.size(width = 18.dp, height = 13.dp))
        Spacer(modifier = Modifier.width(9.dp))
        Text(
            text = "AMAN-AI",
            style = SecretSans.copy(
                fontSize = 16.sp,
                lineHeight = 19.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Surface(
            modifier = Modifier.size(32.dp),
            shape = CircleShape,
            color = SecretButton,
            shadowElevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                LockIcon(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun PinProgressDots(
    filled: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.width(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = if (index < filled) Color.White else SecretDotEmpty,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
private fun PinKeypad(
    modifier: Modifier = Modifier,
    onDigitPress: () -> Unit = {},
    onBackspace: () -> Unit = {},
    onBiometricPress: () -> Unit = {}
) {
    Column(
        modifier = modifier.width(244.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(19.dp)
    ) {
        PinDigitRow(listOf("1", "2", "3"), onDigitPress)
        PinDigitRow(listOf("4", "5", "6"), onDigitPress)
        PinDigitRow(listOf("7", "8", "9"), onDigitPress)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(62.dp)
                    .clickable(onClick = onBiometricPress),
                contentAlignment = Alignment.Center
            ) {
                FingerprintIcon(modifier = Modifier.size(18.dp))
            }
            PinDigitButton(number = "0", onClick = onDigitPress)
            Box(
                modifier = Modifier
                    .size(62.dp)
                    .clickable(onClick = onBackspace),
                contentAlignment = Alignment.Center
            ) {
                BackspaceIcon(modifier = Modifier.size(width = 18.dp, height = 14.dp))
            }
        }
    }
}

@Composable
private fun PinDigitRow(numbers: List<String>, onDigitPress: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        numbers.forEach { number ->
            PinDigitButton(number = number, onClick = onDigitPress)
        }
    }
}

@Composable
private fun PinDigitButton(
    number: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .size(62.dp)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = SecretButton,
        shadowElevation = 0.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = number,
                style = SecretSans.copy(
                    fontSize = 23.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun SecretCloudLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(
            width = 1.5.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
        val path = Path().apply {
            moveTo(size.width * 0.11f, size.height * 0.76f)
            quadraticBezierTo(size.width * 0.02f, size.height * 0.55f, size.width * 0.27f, size.height * 0.50f)
            quadraticBezierTo(size.width * 0.38f, size.height * 0.12f, size.width * 0.63f, size.height * 0.32f)
            quadraticBezierTo(size.width * 0.80f, size.height * 0.36f, size.width * 0.82f, size.height * 0.62f)
            quadraticBezierTo(size.width * 0.97f, size.height * 0.65f, size.width * 0.91f, size.height * 0.79f)
            close()
        }
        drawPath(path = path, color = Color.White.copy(alpha = 0.78f), style = stroke)
    }
}

@Composable
private fun LockIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(
            width = 2.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
        val lockColor = Color.White
        drawRoundRect(
            color = lockColor,
            topLeft = Offset(size.width * 0.22f, size.height * 0.43f),
            size = Size(size.width * 0.56f, size.height * 0.43f),
            cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx())
        )
        drawArc(
            color = lockColor,
            startAngle = 200f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(size.width * 0.30f, size.height * 0.12f),
            size = Size(size.width * 0.40f, size.height * 0.50f),
            style = stroke
        )
        drawCircle(
            color = SecretButton,
            radius = size.minDimension * 0.055f,
            center = Offset(size.width * 0.50f, size.height * 0.63f)
        )
    }
}

@Composable
private fun FingerprintIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(
            width = 1.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
        val c = SecretIcon.copy(alpha = 0.86f)
        val center = Offset(size.width / 2f, size.height * 0.55f)
        repeat(5) { index ->
            val inset = index * size.minDimension * 0.08f
            drawArc(
                color = c,
                startAngle = 195f,
                sweepAngle = 150f,
                useCenter = false,
                topLeft = Offset(size.width * 0.12f + inset, size.height * 0.08f + inset),
                size = Size(size.width * 0.76f - inset * 2f, size.height * 0.86f - inset * 2f),
                style = stroke
            )
        }
        drawLine(
            color = c,
            start = Offset(center.x, center.y),
            end = Offset(size.width * 0.50f, size.height * 0.95f),
            strokeWidth = 1.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun BackspaceIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(
            width = 1.6.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
        val c = SecretIcon.copy(alpha = 0.88f)
        val path = Path().apply {
            moveTo(size.width * 0.33f, size.height * 0.12f)
            lineTo(size.width * 0.94f, size.height * 0.12f)
            quadraticBezierTo(size.width, size.height * 0.12f, size.width, size.height * 0.20f)
            lineTo(size.width, size.height * 0.80f)
            quadraticBezierTo(size.width, size.height * 0.88f, size.width * 0.94f, size.height * 0.88f)
            lineTo(size.width * 0.33f, size.height * 0.88f)
            lineTo(size.width * 0.04f, size.height * 0.50f)
            close()
        }
        drawPath(path = path, color = c, style = stroke)
        drawLine(
            color = c,
            start = Offset(size.width * 0.47f, size.height * 0.35f),
            end = Offset(size.width * 0.70f, size.height * 0.65f),
            strokeWidth = 1.5.dp.toPx(),
            cap = StrokeCap.Round
        )
        drawLine(
            color = c,
            start = Offset(size.width * 0.70f, size.height * 0.35f),
            end = Offset(size.width * 0.47f, size.height * 0.65f),
            strokeWidth = 1.5.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

private val SecretBackground = Color(0xFF08046D)
private val SecretButton = Color(0xFF151582)
private val SecretHandle = Color(0xFF22208A)
private val SecretMuted = Color(0xFFB6B5D3)
private val SecretDotEmpty = Color(0xFF4A4B98)
private val SecretIcon = Color(0xFFA9A8D4)

private val SecretSans = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp
)

private val SecretSerif = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp
)

@Preview(name = "Secret Access PIN", showBackground = true, backgroundColor = 0xFF08046D)
@Composable
private fun SecretAccessPinScreenPreview() {
    AmanAiTheme {
        SecretAccessPinScreen()
    }
}
