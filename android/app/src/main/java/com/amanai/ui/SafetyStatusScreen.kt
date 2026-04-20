package com.amanai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanai.ui.theme.TealPrim

@Composable
fun SafetyStatusScreen(onTriggerAlert: () -> Unit = {}) {
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
                    label = { Text("Safety") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Settings") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F7FA))
                .padding(24.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Aman AI",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TealPrim
                )
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Notifications, contentDescription = null)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Weather Widget (Simplified)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("San Francisco, CA", fontSize = 14.sp, color = Color.Gray)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("22°C", fontSize = 48.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Partly Cloudy", fontSize = 16.sp)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Safety Status Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTriggerAlert() },
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = TealPrim)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(64.dp),
                        shape = RoundedCornerShape(16.dp),
                        color = Color.White.copy(alpha = 0.2f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("🛡️", fontSize = 32.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Safety Status: SAFE", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("AI monitoring active", color = Color.White.copy(alpha = 0.8f))
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Bottom stats/options
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                StatCard(modifier = Modifier.weight(1f), title = "SOS", subtitle = "Trigger")
                StatCard(modifier = Modifier.weight(1f), title = "History", subtitle = "12 alerts")
            }
        }
    }
}

@Composable
fun StatCard(modifier: Modifier, title: String, subtitle: String) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}
