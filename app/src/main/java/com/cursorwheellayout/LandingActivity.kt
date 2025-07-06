package com.cursorwheellayout

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class LandingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF13171C)
                ) {
                    val context = LocalContext.current
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = { context.startActivity(Intent(context, ComposeMainActivity::class.java)) }) {
                            Text("Compose Main Demo")
                        }
                        Button(onClick = { context.startActivity(Intent(context, AdvancedComposeWheelActivity::class.java)) }) {
                            Text("Advanced Compose Demo")
                        }
                        Button(onClick = { context.startActivity(Intent(context, ComposeWheelActivity::class.java)) }) {
                            Text("Compose Wheel Demo")
                        }
                        Button(onClick = { context.startActivity(Intent(context, ComposeInteroperabilityActivity::class.java)) }) {
                            Text("Compose Interoperability Demo")
                        }
                    }
                }
            }
        }
    }
}