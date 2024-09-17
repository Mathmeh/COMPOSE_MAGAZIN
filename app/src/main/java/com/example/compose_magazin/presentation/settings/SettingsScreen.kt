package com.example.compose_magazin.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFE0F7FA)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Settings Screen", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("catalog") }) {
                Text("Go to Catalog")
            }
        }
    }
}
