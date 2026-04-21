package com.amanai.app

import androidx.compose.runtime.Composable
import com.amanai.app.navigation.AppNavGraph
import com.amanai.app.theme.AmanAiTheme

@Composable
fun AmanAiApp() {
    AmanAiTheme {
        AppNavGraph()
    }
}
