package com.amanai.features.onboarding

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanai.app.theme.AmanAiTheme

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinue: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        OnboardingBlueTop,
                        OnboardingBlueMid,
                        OnboardingBlueBottom
                    ),
                    start = Offset.Zero,
                    end = Offset(900f, 1600f)
                )
            )
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 27.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(74.dp))
            ShieldFeatureCard()
            Spacer(modifier = Modifier.height(24.dp))
            OnboardingIndicators()
            Spacer(modifier = Modifier.height(86.dp))
            OnboardingTitle()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "A discreet security layer built for your\npeace of mind. Experience protection that\nstays silent until you need it most, hidden\nbeneath a familiar interface.",
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 11.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = OnboardingParagraph,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(36.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BenefitCard(
                    modifier = Modifier.weight(1f),
                    icon = BenefitIconType.Discreet,
                    title = "Discreet Mode",
                    description = "Hidden from the\npublic view."
                )
                BenefitCard(
                    modifier = Modifier.weight(1f),
                    icon = BenefitIconType.Vault,
                    title = "Secure Vault",
                    description = "Military-grade\nprotection."
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            ContinueButton(onClick = onContinue)
            Spacer(modifier = Modifier.height(18.dp))
            LegalCaption()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ShieldFeatureCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(width = 86.dp, height = 86.dp)
            .border(
                width = 1.dp,
                color = OnboardingCardBorder,
                shape = RoundedCornerShape(28.dp)
            ),
        shape = RoundedCornerShape(28.dp),
        color = OnboardingCard
    ) {
        Box(contentAlignment = Alignment.Center) {
            ShieldHeartIcon(modifier = Modifier.size(34.dp))
        }
    }
}

@Composable
private fun ShieldHeartIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val shield = Path().apply {
            moveTo(size.width * 0.50f, size.height * 0.06f)
            lineTo(size.width * 0.82f, size.height * 0.18f)
            lineTo(size.width * 0.75f, size.height * 0.56f)
            quadraticBezierTo(size.width * 0.69f, size.height * 0.82f, size.width * 0.50f, size.height * 0.96f)
            quadraticBezierTo(size.width * 0.31f, size.height * 0.82f, size.width * 0.25f, size.height * 0.56f)
            lineTo(size.width * 0.18f, size.height * 0.18f)
            close()
        }
        drawPath(path = shield, color = OnboardingIcon)

        val heart = Path().apply {
            moveTo(size.width * 0.50f, size.height * 0.69f)
            cubicTo(size.width * 0.36f, size.height * 0.58f, size.width * 0.27f, size.height * 0.48f, size.width * 0.27f, size.height * 0.37f)
            cubicTo(size.width * 0.27f, size.height * 0.28f, size.width * 0.34f, size.height * 0.22f, size.width * 0.42f, size.height * 0.22f)
            cubicTo(size.width * 0.47f, size.height * 0.22f, size.width * 0.50f, size.height * 0.26f, size.width * 0.50f, size.height * 0.26f)
            cubicTo(size.width * 0.50f, size.height * 0.26f, size.width * 0.53f, size.height * 0.22f, size.width * 0.58f, size.height * 0.22f)
            cubicTo(size.width * 0.66f, size.height * 0.22f, size.width * 0.73f, size.height * 0.28f, size.width * 0.73f, size.height * 0.37f)
            cubicTo(size.width * 0.73f, size.height * 0.48f, size.width * 0.64f, size.height * 0.58f, size.width * 0.50f, size.height * 0.69f)
            close()
        }
        drawPath(path = heart, color = OnboardingBlueTop)
    }
}

@Composable
private fun OnboardingIndicators(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.width(102.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IndicatorBar(selected = true)
        IndicatorBar(selected = false)
        IndicatorBar(selected = false)
    }
}

@Composable
private fun IndicatorBar(selected: Boolean) {
    Box(
        modifier = Modifier
            .width(32.dp)
            .height(4.dp)
            .background(
                color = if (selected) Color.White else OnboardingIndicatorOff,
                shape = RoundedCornerShape(2.dp)
            )
    )
}

@Composable
private fun OnboardingTitle(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            pushStyle(
                SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            append("Welcome to ")
            pop()
            pushStyle(
                SpanStyle(
                    color = OnboardingAccentText,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            append("Aman")
            append("\n")
            append("Sanctuary")
            pop()
        },
        style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontSize = 28.sp,
            lineHeight = 33.sp
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun BenefitCard(
    icon: BenefitIconType,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(115.dp),
        shape = RoundedCornerShape(22.dp),
        color = OnboardingFeatureCard
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            BenefitIcon(type = icon, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    lineHeight = 17.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = description,
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 9.sp,
                    lineHeight = 13.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = OnboardingFeatureText
            )
        }
    }
}

@Composable
private fun BenefitIcon(
    type: BenefitIconType,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(
            width = 1.6.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
        when (type) {
            BenefitIconType.Discreet -> {
                drawOval(
                    color = OnboardingIcon,
                    topLeft = Offset(size.width * 0.14f, size.height * 0.26f),
                    size = Size(size.width * 0.72f, size.height * 0.42f),
                    style = stroke
                )
                drawCircle(
                    color = OnboardingIcon,
                    radius = size.minDimension * 0.11f,
                    center = Offset(size.width * 0.50f, size.height * 0.47f)
                )
                drawLine(
                    color = OnboardingIcon,
                    start = Offset(size.width * 0.16f, size.height * 0.82f),
                    end = Offset(size.width * 0.84f, size.height * 0.14f),
                    strokeWidth = 1.6.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            BenefitIconType.Vault -> {
                drawRoundRect(
                    color = OnboardingIcon,
                    topLeft = Offset(size.width * 0.26f, size.height * 0.46f),
                    size = Size(size.width * 0.48f, size.height * 0.34f),
                    cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                    style = stroke
                )
                drawArc(
                    color = OnboardingIcon,
                    startAngle = 200f,
                    sweepAngle = 140f,
                    useCenter = false,
                    topLeft = Offset(size.width * 0.33f, size.height * 0.14f),
                    size = Size(size.width * 0.34f, size.height * 0.42f),
                    style = stroke
                )
            }
        }
    }
}

@Composable
private fun ContinueButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(onClick = onClick)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        OnboardingButtonStart,
                        OnboardingButtonEnd
                    )
                ),
                shape = RoundedCornerShape(28.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Continue \u2192",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 21.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            color = OnboardingButtonText,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LegalCaption(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = buildAnnotatedString {
            append("By continuing, you agree to our ")
            pushStyle(
                SpanStyle(
                    color = OnboardingLegalLink,
                    textDecoration = TextDecoration.Underline
                )
            )
            append("Privacy Standards")
            pop()
            append(" and\n")
            pushStyle(
                SpanStyle(
                    color = OnboardingLegalLink,
                    textDecoration = TextDecoration.Underline
                )
            )
            append("Terms of Protection.")
            pop()
        },
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 8.sp,
            lineHeight = 11.sp,
            fontWeight = FontWeight.Medium
        ),
        color = OnboardingLegal,
        textAlign = TextAlign.Center
    )
}

private enum class BenefitIconType {
    Discreet,
    Vault
}

private val OnboardingBlueTop = Color(0xFF09117C)
private val OnboardingBlueMid = Color(0xFF071071)
private val OnboardingBlueBottom = Color(0xFF0A0D5E)
private val OnboardingCard = Color(0xFF12217B)
private val OnboardingCardBorder = Color(0xFF22328C)
private val OnboardingIcon = Color(0xFFAAB0FF)
private val OnboardingIndicatorOff = Color(0xFF5163A8)
private val OnboardingAccentText = Color(0xFF9FA7FF)
private val OnboardingParagraph = Color(0xFFF1F2FF)
private val OnboardingFeatureCard = Color(0xFF111A74)
private val OnboardingFeatureText = Color(0xFF969CC7)
private val OnboardingButtonStart = Color(0xFFFBFCF6)
private val OnboardingButtonEnd = Color(0xFFE8F1DA)
private val OnboardingButtonText = Color(0xFF101776)
private val OnboardingLegal = Color(0xFF7F88B7)
private val OnboardingLegalLink = Color(0xFF8E96C6)

@Preview(name = "Onboarding", showBackground = true, backgroundColor = 0xFF09117C)
@Composable
private fun OnboardingScreenPreview() {
    AmanAiTheme {
        OnboardingScreen()
    }
}
