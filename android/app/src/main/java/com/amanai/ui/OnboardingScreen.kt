package com.amanai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A192F)) // Deep navy from Figma
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        
        Surface(
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFF172A45)
        ) {
            Box(contentAlignment = Alignment.Center) {
                // Shield icon placeholder
                Text("🛡️", fontSize = 48.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = "Welcome to Aman Sanctuary",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Advanced safety system utilizing AI to protect you in any situation, online or offline.",
            color = Color.LightGray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = onFinished,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007096))
        ) {
            Text("Continue", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
