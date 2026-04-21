package com.amanai.features.trust_contacts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun TrustContactsScreen(
    modifier: Modifier = Modifier,
    onSaveContinue: () -> Unit = {},
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(TrustBackground)
    ) {
        TrustContactsHeader(
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
                .padding(bottom = 98.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(13.dp))
            SecurityLayerPill()
            Spacer(modifier = Modifier.height(14.dp))
            TrustTitle()
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Add up to 3 trust contacts who will be\nnotified instantly in case of an emergency.",
                style = TrustBodyStyle.copy(
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = TrustBodyText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(18.dp))

            PrimaryContactCard()
            Spacer(modifier = Modifier.height(14.dp))
            SecondaryContactCard()
            Spacer(modifier = Modifier.height(14.dp))
            InfoTrustCard()
            Spacer(modifier = Modifier.height(12.dp))
            EncryptedCard()
            Spacer(modifier = Modifier.height(18.dp))
            SaveContinueButton(onClick = onSaveContinue)
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Setup later",
                style = TrustBodyStyle.copy(
                    fontSize = 8.sp,
                    lineHeight = 10.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = TrustMuted
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        TrustBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun TrustContactsHeader(modifier: Modifier = Modifier) {
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
            TrustCloudLogo(modifier = Modifier.size(width = 15.dp, height = 10.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "AMAN-AI",
                style = TrustBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = TrustNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            TrustAvatar()
        }
    }
}

@Composable
private fun SecurityLayerPill() {
    Row(
        modifier = Modifier
            .height(18.dp)
            .background(TrustPillGreen, RoundedCornerShape(9.dp))
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShieldMiniIcon(modifier = Modifier.size(9.dp), tint = TrustNavy)
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "SECURITY LAYER",
            style = TrustBodyStyle.copy(
                fontSize = 6.sp,
                lineHeight = 8.sp,
                fontWeight = FontWeight.Bold
            ),
            color = TrustNavy
        )
    }
}

@Composable
private fun TrustTitle() {
    Text(
        text = "Trust contacts\nsetup",
        style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontSize = 22.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.ExtraBold
        ),
        color = TrustNavy,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun PrimaryContactCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Row {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(142.dp)
                    .background(TrustGreen, RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ContactBadge(text = "CONTACT 01", color = TrustGreen, textColor = Color.White)
                    Spacer(modifier = Modifier.weight(1f))
                    ImportContactsButton()
                }
                Spacer(modifier = Modifier.height(12.dp))
                FieldLabel(text = "FULL NAME", active = true)
                Spacer(modifier = Modifier.height(5.dp))
                ContactField(text = "e.g. Sarah Jenkins", placeholder = true)
                Spacer(modifier = Modifier.height(10.dp))
                FieldLabel(text = "PHONE NUMBER", active = true)
                Spacer(modifier = Modifier.height(5.dp))
                ContactField(text = "+1 (555) 000-000", placeholder = true)
            }
        }
    }
}

@Composable
private fun SecondaryContactCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = TrustDashedBorder,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            ContactBadge(text = "CONTACT 02", color = TrustBadgeGray, textColor = TrustMuted)
            Spacer(modifier = Modifier.height(13.dp))
            FieldLabel(text = "FULL NAME", active = false)
            Spacer(modifier = Modifier.height(5.dp))
            ContactField(text = "Optional", placeholder = true, enabled = false)
            Spacer(modifier = Modifier.height(11.dp))
            FieldLabel(text = "PHONE NUMBER", active = false)
            Spacer(modifier = Modifier.height(5.dp))
            ContactField(text = "Optional", placeholder = true, enabled = false)
            Spacer(modifier = Modifier.height(14.dp))
            AddSecondContactButton()
        }
    }
}

@Composable
private fun ContactBadge(text: String, color: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .height(16.dp)
            .background(color, RoundedCornerShape(8.dp))
            .padding(horizontal = 9.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TrustBodyStyle.copy(
                fontSize = 6.sp,
                lineHeight = 8.sp,
                fontWeight = FontWeight.Bold
            ),
            color = textColor
        )
    }
}

@Composable
private fun ImportContactsButton() {
    Row(
        modifier = Modifier
            .height(18.dp)
            .background(TrustImportBg, RoundedCornerShape(9.dp))
            .padding(horizontal = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImportIcon(modifier = Modifier.size(9.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Import from contacts",
            style = TrustBodyStyle.copy(
                fontSize = 6.sp,
                lineHeight = 8.sp,
                fontWeight = FontWeight.Bold
            ),
            color = TrustNavy
        )
    }
}

@Composable
private fun FieldLabel(text: String, active: Boolean) {
    Text(
        text = text,
        style = TrustBodyStyle.copy(
            fontSize = 6.sp,
            lineHeight = 8.sp,
            fontWeight = FontWeight.Bold
        ),
        color = if (active) TrustFieldLabel else TrustMuted
    )
}

@Composable
private fun ContactField(text: String, placeholder: Boolean, enabled: Boolean = true) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .background(
                color = if (enabled) TrustFieldBg else TrustFieldDisabled,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            style = TrustBodyStyle.copy(
                fontSize = 9.sp,
                lineHeight = 11.sp,
                fontWeight = FontWeight.Medium
            ),
            color = if (placeholder) TrustFieldPlaceholder else TrustNavy
        )
    }
}

@Composable
private fun AddSecondContactButton() {
    Row(
        modifier = Modifier
            .height(22.dp)
            .background(TrustImportBg, RoundedCornerShape(11.dp))
            .padding(horizontal = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "+",
            style = TrustBodyStyle.copy(
                fontSize = 10.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.Bold
            ),
            color = TrustNavy
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "Add Second Contact",
            style = TrustBodyStyle.copy(
                fontSize = 7.sp,
                lineHeight = 9.sp,
                fontWeight = FontWeight.Bold
            ),
            color = TrustNavy
        )
    }
}

@Composable
private fun InfoTrustCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color.Transparent,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(TrustInfoTop, TrustInfoBottom)
                    ),
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(horizontal = 14.dp, vertical = 13.dp)
        ) {
            Column {
                Text(
                    text = "How it works",
                    style = TrustBodyStyle.copy(
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "When an alert is triggered, these contacts\nreceive your real-time location via secure SMS\nand an automated AMAN-AI voice call.",
                    style = TrustBodyStyle.copy(
                        fontSize = 8.sp,
                        lineHeight = 13.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = TrustInfoText
                )
            }
        }
    }
}

@Composable
private fun EncryptedCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = TrustEncrypted,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShieldMiniIcon(modifier = Modifier.size(16.dp), tint = TrustNavy)
            Spacer(modifier = Modifier.width(9.dp))
            Column {
                Text(
                    text = "Encrypted",
                    style = TrustBodyStyle.copy(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = TrustNavy
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "End-to-end data safety",
                    style = TrustBodyStyle.copy(
                        fontSize = 7.sp,
                        lineHeight = 9.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = TrustNavy.copy(alpha = 0.65f)
                )
            }
        }
    }
}

@Composable
private fun SaveContinueButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(39.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        color = TrustNavy,
        shadowElevation = 8.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "Save & Continue",
                style = TrustBodyStyle.copy(
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
private fun TrustBottomNav(
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
            TrustNavItem(type = TrustIconType.Weather, label = "Weather", selected = false, onClick = onWeatherClick)
            TrustNavItem(type = TrustIconType.Forecast, label = "Forecast", selected = false, onClick = onForecastClick)
            TrustNavItem(type = TrustIconType.Safety, label = "Safety", selected = true, onClick = onSafetyClick)
            TrustNavItem(type = TrustIconType.Settings, label = "Settings", selected = false, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun TrustNavItem(type: TrustIconType, label: String, selected: Boolean, onClick: () -> Unit) {
    val tint = if (selected) TrustNavy else TrustNavMuted
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
                    .background(TrustSelectedPill, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                TrustIcon(type = type, modifier = Modifier.size(15.dp), tint = tint)
            }
            Spacer(modifier = Modifier.height(3.dp))
        } else {
            Spacer(modifier = Modifier.height(5.dp))
            TrustIcon(type = type, modifier = Modifier.size(15.dp), tint = tint)
            Spacer(modifier = Modifier.height(7.dp))
        }
        Text(
            text = label,
            style = TrustBodyStyle.copy(
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
private fun TrustAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(20.dp)) {
        drawCircle(color = TrustNavy)
        drawCircle(
            color = TrustAvatarFace,
            radius = size.minDimension * 0.18f,
            center = Offset(size.width * 0.50f, size.height * 0.36f)
        )
        drawOval(
            color = TrustAvatarFace,
            topLeft = Offset(size.width * 0.30f, size.height * 0.52f),
            size = Size(size.width * 0.40f, size.height * 0.22f)
        )
        drawCircle(
            color = TrustGreen,
            radius = size.minDimension * 0.12f,
            center = Offset(size.width * 0.76f, size.height * 0.32f)
        )
    }
}

@Composable
private fun TrustCloudLogo(modifier: Modifier = Modifier) {
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
        drawPath(path = path, color = TrustNavy, style = stroke)
    }
}

@Composable
private fun ShieldMiniIcon(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier) {
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
}

@Composable
private fun ImportIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawLine(TrustNavy, Offset(size.width * 0.18f, size.height * 0.78f), Offset(size.width * 0.82f, size.height * 0.78f), strokeWidth = 1.2.dp.toPx(), cap = StrokeCap.Round)
        drawLine(TrustNavy, Offset(size.width * 0.50f, size.height * 0.18f), Offset(size.width * 0.50f, size.height * 0.62f), strokeWidth = 1.2.dp.toPx(), cap = StrokeCap.Round)
        drawLine(TrustNavy, Offset(size.width * 0.34f, size.height * 0.46f), Offset(size.width * 0.50f, size.height * 0.62f), strokeWidth = 1.2.dp.toPx(), cap = StrokeCap.Round)
        drawLine(TrustNavy, Offset(size.width * 0.66f, size.height * 0.46f), Offset(size.width * 0.50f, size.height * 0.62f), strokeWidth = 1.2.dp.toPx(), cap = StrokeCap.Round)
        drawCircle(TrustNavy, radius = size.minDimension * 0.34f, center = Offset(size.width / 2f, size.height / 2f), style = stroke)
    }
}

@Composable
private fun TrustIcon(
    type: TrustIconType,
    modifier: Modifier = Modifier,
    tint: Color = TrustNavy
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            TrustIconType.Weather, TrustIconType.Forecast -> {
                drawCircle(tint, radius = size.minDimension * 0.18f, center = Offset(size.width * 0.38f, size.height * 0.58f))
                drawCircle(tint, radius = size.minDimension * 0.23f, center = Offset(size.width * 0.56f, size.height * 0.50f))
                drawOval(tint, topLeft = Offset(size.width * 0.18f, size.height * 0.56f), size = Size(size.width * 0.64f, size.height * 0.22f))
                if (type == TrustIconType.Forecast) {
                    drawLine(tint, Offset(size.width * 0.32f, size.height * 0.90f), Offset(size.width * 0.68f, size.height * 0.90f), strokeWidth = 1.3.dp.toPx(), cap = StrokeCap.Round)
                }
            }
            TrustIconType.Safety -> {
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
            TrustIconType.Settings -> {
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

private enum class TrustIconType {
    Weather,
    Forecast,
    Safety,
    Settings
}

private val TrustBackground = Color(0xFFF3F3F3)
private val TrustNavy = Color(0xFF11156D)
private val TrustGreen = Color(0xFF2BC56B)
private val TrustPillGreen = Color(0xFF8DF184)
private val TrustBodyText = Color(0xFF555B71)
private val TrustMuted = Color(0xFFADB1BE)
private val TrustBadgeGray = Color(0xFFF0F1F5)
private val TrustDashedBorder = Color(0xFFE0E2EA)
private val TrustFieldBg = Color(0xFFF1F2F6)
private val TrustFieldDisabled = Color(0xFFF5F6F9)
private val TrustFieldLabel = Color(0xFF6A7088)
private val TrustFieldPlaceholder = Color(0xFFA3A8B7)
private val TrustImportBg = Color(0xFFE9ECFF)
private val TrustInfoTop = Color(0xFF232D98)
private val TrustInfoBottom = Color(0xFF141A6A)
private val TrustInfoText = Color(0xFFA4A9DF)
private val TrustEncrypted = Color(0xFF8CF17E)
private val TrustSelectedPill = Color(0xFFE5EAFF)
private val TrustNavMuted = Color(0xFFA0A6C8)
private val TrustAvatarFace = Color(0xFFE3B987)

private val TrustBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.sp
)

@Preview(name = "Trust Contacts", showBackground = true, backgroundColor = 0xFFF3F3F3)
@Composable
private fun TrustContactsScreenPreview() {
    AmanAiTheme {
        TrustContactsScreen()
    }
}
