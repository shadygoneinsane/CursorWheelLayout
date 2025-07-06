package com.cursorwheellayout

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursorwheel.compose.CursorWheelLayout
import com.cursorwheel.compose.CursorWheelState
import com.cursorwheel.compose.rememberCursorWheelState
import com.cursorwheel.demo.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class ComposeInteroperabilityActivity : AppCompatActivity() {

    private lateinit var wheelState: CursorWheelState
    private val items = (1..10).map { "Item $it" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_compose)

        val composeView = findViewById<ComposeView>(R.id.compose_view_wheel)
        val randomButton = findViewById<Button>(R.id.btn_random_select)

        composeView.setContent {
            wheelState = rememberCursorWheelState()
            CursorWheelLayout(
                items = items,
                state = wheelState,
                wheelSize = 300.dp,
                itemSize = 60.dp,
                selectedAngle = -90f,
                onItemSelected = { index, item ->
                    Toast.makeText(this, "Selected: $item", Toast.LENGTH_SHORT).show()
                },
                onItemClick = { index, item ->
                    Toast.makeText(this, "Clicked: $item", Toast.LENGTH_SHORT).show()
                }
            ) { _, item, isSelected ->
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color(0xFFFFC52A) else Color.Gray.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item, fontSize = 16.sp, color = if (isSelected) Color.Black else Color.White)
                }
            }
        }

        randomButton.setOnClickListener {
            val randomIndex = Random.nextInt(items.size)
            val angleStep = if (items.size > 0) 360f / items.size else 0f
            CoroutineScope(Dispatchers.Main).launch {
                wheelState.selectItem(randomIndex, items.size, angleStep, -90f)
            }
            Toast.makeText(this, "Randomly selected index: $randomIndex", Toast.LENGTH_SHORT).show()
        }
    }
}