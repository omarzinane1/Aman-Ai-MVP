package com.amanai.features.safety_settings

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
fun SafetySettingsScreen(
    modifier: Modifier = Modifier,
    onEmergencyContactsClick: () -> Unit = {},
    onWeatherClick: () -> Unit = {},
    onForecastClick: () -> Unit = {},
    onSafetyClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SafetySettingsBackground)
    ) {
        SettingsHeader(
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
            Text(
                text = "Settings",
                style = SettingsTitleStyle,
                color = SettingsNavy
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Manage your safety protocols and account\nsecurity.",
                style = SettingsBodyStyle.copy(
                    fontSize = 9.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = SettingsBody
            )
            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader(
                icon = SettingsSectionIcon.Detection,
                text = "AI DETECTION SENSITIVITY"
            )
            Spacer(modifier = Modifier.height(10.dp))
            DetectionSensitivityCard()
            Spacer(modifier = Modifier.height(14.dp))

            SectionHeader(
                icon = SettingsSectionIcon.Vault,
                text = "VAULT SECURITY"
            )
            Spacer(modifier = Modifier.height(10.dp))
            VaultSecurityCard()
            Spacer(modifier = Modifier.height(14.dp))

            SectionHeader(
                icon = SettingsSectionIcon.Account,
                text = "ACCOUNT DETAILS"
            )
            Spacer(modifier = Modifier.height(10.dp))
            AccountDetailsCard(onEmergencyContactsClick = onEmergencyContactsClick)
            Spacer(modifier = Modifier.height(14.dp))
            DangerZoneCard()
            Spacer(modifier = Modifier.height(12.dp))
        }

        SettingsBottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onWeatherClick = onWeatherClick,
            onForecastClick = onForecastClick,
            onSafetyClick = onSafetyClick,
            onSettingsClick = onSettingsClick
        )
    }
}

@Composable
private fun SettingsHeader(modifier: Modifier = Modifier) {
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
                style = SettingsBodyStyle.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SettingsNavy
            )
            Spacer(modifier = Modifier.weight(1f))
            HeaderAvatar()
        }
    }
}

@Composable
private fun SectionHeader(icon: SettingsSectionIcon, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        SectionIcon(
            type = icon,
            modifier = Modifier.size(11.dp),
            tint = SettingsSectionIconColor
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = SettingsCapsStyle.copy(
                fontSize = 7.sp,
                lineHeight = 9.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.9.sp
            ),
            color = SettingsSectionText
        )
    }
}

@Composable
private fun DetectionSensitivityCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Threat Awareness",
                    style = SettingsBodyStyle.copy(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = SettingsNavy
                )
                Spacer(modifier = Modifier.weight(1f))
                StatusPill(text = "OPTIMIZED")
            }
            Spacer(modifier = Modifier.height(10.dp))
            SensitivityScale()
            Spacer(modifier = Modifier.height(14.dp))
            SettingToggleRow(
                title = "Auto-Escalation",
                subtitle = "AI will notify authorities if high risk is detected.",
                checked = true
            )
        }
    }
}

@Composable
private fun StatusPill(text: String) {
    Box(
        modifier = Modifier
            .height(16.dp)
            .background(SettingsGreenSoft, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = SettingsCapsStyle.copy(
                fontSize = 6.sp,
                lineHeight = 8.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.6.sp
            ),
            color = SettingsGreenText
        )
    }
}

@Composable
private fun SensitivityScale() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(SettingsScaleTrack, RoundedCornerShape(2.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.58f)
                    .height(3.dp)
                    .background(SettingsScaleActive, RoundedCornerShape(2.dp))
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "PASSIVE",
                style = SettingsCapsStyle.copy(fontSize = 5.sp, lineHeight = 7.sp),
                color = SettingsScaleMuted
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "BALANCED",
                style = SettingsCapsStyle.copy(
                    fontSize = 5.sp,
                    lineHeight = 7.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SettingsScaleFocused
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "VIGILANT",
                style = SettingsCapsStyle.copy(fontSize = 5.sp, lineHeight = 7.sp),
                color = SettingsScaleMuted
            )
        }
    }
}

@Composable
private fun SettingToggleRow(title: String, subtitle: String, checked: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = SettingsBodyStyle.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SettingsText
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = subtitle,
                style = SettingsBodyStyle.copy(
                    fontSize = 7.sp,
                    lineHeight = 10.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = SettingsBody
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        TogglePill(checked = checked, dark = true)
    }
}

@Composable
private fun VaultSecurityCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = SettingsVaultBlue,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoLeadingIcon(
                    icon = SettingsRowIcon.Pin,
                    dark = true
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Master PIN",
                        style = SettingsBodyStyle.copy(
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Last updated 12 days ago",
                        style = SettingsBodyStyle.copy(
                            fontSize = 6.sp,
                            lineHeight = 8.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = SettingsVaultSubtext
                    )
                }
                Box(
                    modifier = Modifier
                        .height(18.dp)
                        .background(SettingsChangePill, RoundedCornerShape(9.dp))
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CHANGE",
                        style = SettingsCapsStyle.copy(
                            fontSize = 6.sp,
                            lineHeight = 8.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(SettingsVaultDivider)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoLeadingIcon(
                    icon = SettingsRowIcon.Biometric,
                    dark = true
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Biometric Unlock",
                        style = SettingsBodyStyle.copy(
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Use FaceID or Fingerprint",
                        style = SettingsBodyStyle.copy(
                            fontSize = 6.sp,
                            lineHeight = 8.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = SettingsVaultSubtext
                    )
                }
                TogglePill(checked = true, dark = false)
            }
        }
    }
}

@Composable
private fun TogglePill(checked: Boolean, dark: Boolean) {
    val track = if (checked) {
        if (dark) SettingsNavy else SettingsGreenTrack
    } else {
        if (dark) SettingsToggleOff else SettingsToggleOffLight
    }
    val thumb = if (checked) Color.White else SettingsThumbOff

    Box(
        modifier = Modifier
            .width(30.dp)
            .height(16.dp)
            .background(track, RoundedCornerShape(10.dp))
            .padding(horizontal = 2.dp),
        contentAlignment = if (checked) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(11.dp)
                .background(thumb, CircleShape)
        )
    }
}

@Composable
private fun AccountDetailsCard(onEmergencyContactsClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = SettingsCardSoft,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            AccountRow(
                icon = SettingsRowIcon.Email,
                title = "Email Address",
                subtitle = "aman.safe@example.com",
                showChevron = true,
                danger = false,
                onClick = null
            )
            AccountRow(
                icon = SettingsRowIcon.Contacts,
                title = "Emergency Contacts",
                subtitle = "3 contacts registered",
                showChevron = true,
                danger = false,
                onClick = onEmergencyContactsClick
            )
            AccountRow(
                icon = SettingsRowIcon.SignOut,
                title = "Sign Out",
                subtitle = null,
                showChevron = false,
                danger = true,
                onClick = null
            )
        }
    }
}

@Composable
private fun AccountRow(
    icon: SettingsRowIcon,
    title: String,
    subtitle: String?,
    showChevron: Boolean,
    danger: Boolean,
    onClick: (() -> Unit)?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoLeadingIcon(
            icon = icon,
            dark = false,
            danger = danger
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = SettingsBodyStyle.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = if (danger) SettingsDanger else SettingsText
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = SettingsBodyStyle.copy(
                        fontSize = 6.sp,
                        lineHeight = 8.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = SettingsBody
                )
            }
        }
        if (showChevron) {
            ChevronIcon(
                modifier = Modifier.size(12.dp),
                tint = SettingsChevron
            )
        }
    }
}

@Composable
private fun DangerZoneCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = SettingsDangerCard,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(15.dp)
                    .background(SettingsDangerPill, RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "DANGER ZONE",
                    style = SettingsCapsStyle.copy(
                        fontSize = 5.sp,
                        lineHeight = 7.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.8.sp
                    ),
                    color = SettingsDanger
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Deleting your account will\npermanently wipe all\nencrypted logs and\ndata backups.",
                style = SettingsBodyStyle.copy(
                    fontSize = 8.sp,
                    lineHeight = 11.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = SettingsDangerText
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "DELETE ACCOUNT",
                style = SettingsCapsStyle.copy(
                    fontSize = 6.sp,
                    lineHeight = 8.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.7.sp
                ),
                color = SettingsDanger
            )
        }
    }
}

@Composable
private fun InfoLeadingIcon(
    icon: SettingsRowIcon,
    dark: Boolean,
    danger: Boolean = false
) {
    val tint = when {
        danger -> SettingsDanger
        dark -> Color.White
        else -> SettingsIcon
    }

    Box(
        modifier = Modifier.size(16.dp),
        contentAlignment = Alignment.Center
    ) {
        RowIcon(
            type = icon,
            modifier = Modifier.size(14.dp),
            tint = tint
        )
    }
}

@Composable
private fun SettingsBottomNav(
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
            BottomNavItem(type = BottomNavType.Safety, label = "Safety", selected = false, onClick = onSafetyClick)
            BottomNavItem(type = BottomNavType.Settings, label = "Settings", selected = true, onClick = onSettingsClick)
        }
    }
}

@Composable
private fun BottomNavItem(type: BottomNavType, label: String, selected: Boolean, onClick: () -> Unit) {
    val tint = if (selected) SettingsNavy else SettingsNavMuted
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
                    .background(SettingsSelectedPill, CircleShape),
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
            style = SettingsBodyStyle.copy(
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
private fun HeaderAvatar(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(22.dp)) {
        drawCircle(color = SettingsAvatarBg)
        drawCircle(
            color = SettingsAvatarStroke,
            radius = size.minDimension * 0.16f,
            center = Offset(size.width * 0.50f, size.height * 0.37f),
            style = Stroke(width = 1.2.dp.toPx())
        )
        drawArc(
            color = SettingsAvatarStroke,
            startAngle = 200f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(size.width * 0.28f, size.height * 0.44f),
            size = Size(size.width * 0.44f, size.height * 0.28f),
            style = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round)
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
        drawPath(path = path, color = SettingsNavy, style = stroke)
    }
}

@Composable
private fun SectionIcon(type: SettingsSectionIcon, modifier: Modifier = Modifier, tint: Color = SettingsSectionIconColor) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            SettingsSectionIcon.Detection -> {
                drawCircle(tint, radius = size.minDimension * 0.18f, center = Offset(size.width * 0.50f, size.height * 0.44f), style = stroke)
                drawLine(tint, Offset(size.width * 0.50f, size.height * 0.64f), Offset(size.width * 0.50f, size.height * 0.92f), 1.2.dp.toPx(), StrokeCap.Round)
            }
            SettingsSectionIcon.Vault -> {
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(size.width * 0.24f, size.height * 0.42f),
                    size = Size(size.width * 0.52f, size.height * 0.38f),
                    style = stroke
                )
                drawArc(
                    color = tint,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.34f, size.height * 0.18f),
                    size = Size(size.width * 0.32f, size.height * 0.34f),
                    style = stroke
                )
            }
            SettingsSectionIcon.Account -> {
                drawCircle(tint, radius = size.minDimension * 0.14f, center = Offset(size.width * 0.50f, size.height * 0.34f), style = stroke)
                drawArc(
                    color = tint,
                    startAngle = 200f,
                    sweepAngle = 140f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.26f, size.height * 0.44f),
                    size = Size(size.width * 0.48f, size.height * 0.30f),
                    style = stroke
                )
            }
        }
    }
}

@Composable
private fun RowIcon(type: SettingsRowIcon, modifier: Modifier = Modifier, tint: Color = SettingsIcon) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        when (type) {
            SettingsRowIcon.Pin -> {
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(size.width * 0.20f, size.height * 0.18f),
                    size = Size(size.width * 0.60f, size.height * 0.64f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                    style = stroke
                )
                drawLine(tint, Offset(size.width * 0.35f, size.height * 0.40f), Offset(size.width * 0.65f, size.height * 0.40f), 1.2.dp.toPx(), StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.50f, size.height * 0.34f), Offset(size.width * 0.50f, size.height * 0.62f), 1.2.dp.toPx(), StrokeCap.Round)
            }
            SettingsRowIcon.Biometric -> {
                drawArc(
                    color = tint,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.22f, size.height * 0.18f),
                    size = Size(size.width * 0.56f, size.height * 0.64f),
                    style = stroke
                )
                drawLine(tint, Offset(size.width * 0.50f, size.height * 0.26f), Offset(size.width * 0.50f, size.height * 0.74f), 1.2.dp.toPx(), StrokeCap.Round)
                drawArc(
                    color = tint,
                    startAngle = 220f,
                    sweepAngle = 100f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.34f, size.height * 0.38f),
                    size = Size(size.width * 0.32f, size.height * 0.28f),
                    style = stroke
                )
            }
            SettingsRowIcon.Email -> {
                drawRoundRect(
                    color = tint,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.24f),
                    size = Size(size.width * 0.64f, size.height * 0.48f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                    style = stroke
                )
                drawLine(tint, Offset(size.width * 0.20f, size.height * 0.30f), Offset(size.width * 0.50f, size.height * 0.52f), 1.2.dp.toPx(), StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.80f, size.height * 0.30f), Offset(size.width * 0.50f, size.height * 0.52f), 1.2.dp.toPx(), StrokeCap.Round)
            }
            SettingsRowIcon.Contacts -> {
                drawCircle(tint, radius = size.minDimension * 0.10f, center = Offset(size.width * 0.38f, size.height * 0.34f), style = stroke)
                drawCircle(tint, radius = size.minDimension * 0.10f, center = Offset(size.width * 0.62f, size.height * 0.34f), style = stroke)
                drawArc(
                    color = tint,
                    startAngle = 200f,
                    sweepAngle = 140f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.18f, size.height * 0.44f),
                    size = Size(size.width * 0.40f, size.height * 0.24f),
                    style = stroke
                )
                drawArc(
                    color = tint,
                    startAngle = 200f,
                    sweepAngle = 140f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.42f, size.height * 0.44f),
                    size = Size(size.width * 0.40f, size.height * 0.24f),
                    style = stroke
                )
            }
            SettingsRowIcon.SignOut -> {
                drawLine(tint, Offset(size.width * 0.22f, size.height * 0.50f), Offset(size.width * 0.62f, size.height * 0.50f), 1.2.dp.toPx(), StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.48f, size.height * 0.34f), Offset(size.width * 0.62f, size.height * 0.50f), 1.2.dp.toPx(), StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.48f, size.height * 0.66f), Offset(size.width * 0.62f, size.height * 0.50f), 1.2.dp.toPx(), StrokeCap.Round)
                drawLine(tint, Offset(size.width * 0.22f, size.height * 0.24f), Offset(size.width * 0.22f, size.height * 0.76f), 1.2.dp.toPx(), StrokeCap.Round)
            }
        }
    }
}

@Composable
private fun ChevronIcon(modifier: Modifier = Modifier, tint: Color = SettingsChevron) {
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
private fun BottomNavIcon(type: BottomNavType, modifier: Modifier = Modifier, tint: Color = SettingsNavy) {
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

private enum class SettingsSectionIcon {
    Detection,
    Vault,
    Account
}

private enum class SettingsRowIcon {
    Pin,
    Biometric,
    Email,
    Contacts,
    SignOut
}

private val SafetySettingsBackground = Color(0xFFF9FAFC)
private val SettingsNavy = Color(0xFF11156D)
private val SettingsText = Color(0xFF1E212B)
private val SettingsBody = Color(0xFF71768A)
private val SettingsSectionText = Color(0xFF848AA2)
private val SettingsSectionIconColor = Color(0xFF5660A5)
private val SettingsScaleTrack = Color(0xFFE8EBF0)
private val SettingsScaleActive = Color(0xFFB8C0F8)
private val SettingsScaleMuted = Color(0xFFB2B6C6)
private val SettingsScaleFocused = Color(0xFF717795)
private val SettingsGreenSoft = Color(0xFFDBF8D7)
private val SettingsGreenText = Color(0xFF259A35)
private val SettingsGreenTrack = Color(0xFF56DD64)
private val SettingsToggleOff = Color(0xFF525A9E)
private val SettingsToggleOffLight = Color(0xFFE5E7EE)
private val SettingsThumbOff = Color(0xFF7A8093)
private val SettingsVaultBlue = Color(0xFF232D98)
private val SettingsVaultSubtext = Color(0xFFAAB1ED)
private val SettingsChangePill = Color(0xFF5560B9)
private val SettingsVaultDivider = Color(0xFF4650AE)
private val SettingsCardSoft = Color(0xFFF3F4F7)
private val SettingsIcon = Color(0xFF6C728D)
private val SettingsChevron = Color(0xFF8E93A8)
private val SettingsDanger = Color(0xFFE54C41)
private val SettingsDangerCard = Color(0xFFFCEFEF)
private val SettingsDangerPill = Color(0xFFFFE4E2)
private val SettingsDangerText = Color(0xFFD04C45)
private val SettingsSelectedPill = Color(0xFFE5EAFF)
private val SettingsNavMuted = Color(0xFFA0A6C8)
private val SettingsAvatarBg = Color(0xFF1A1A1C)
private val SettingsAvatarStroke = Color(0xFFD5D5D5)

private val SettingsTitleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 24.sp,
    lineHeight = 28.sp,
    fontWeight = FontWeight.ExtraBold
)

private val SettingsBodyStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    fontWeight = FontWeight.Normal
)

private val SettingsCapsStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 7.sp,
    lineHeight = 9.sp,
    fontWeight = FontWeight.Medium
)

@Preview(name = "Safety Settings", showBackground = true, backgroundColor = 0xFFF9FAFC)
@Composable
private fun SafetySettingsScreenPreview() {
    AmanAiTheme {
        SafetySettingsScreen()
    }
}
