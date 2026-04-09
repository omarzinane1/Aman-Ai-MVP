package com.amanai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanai.service.DetectionService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(DetectionService.newIntent(this))
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var isActive by remember { mutableStateOf(true) }
    var statusText by remember { mutableStateOf("Protection active") }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "AMAN-AI", fontSize = 28.sp, color = Color(0, 112, 150))
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = statusText, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Annulation manuelle */ }) {
            Text("Annuler alerte (30s)")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = { /* Ouvrir paramètres */ }) {
            Text("Contacts & réglages")
        }
    }
}
