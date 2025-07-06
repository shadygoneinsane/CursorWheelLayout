package com.cursorwheellayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursorwheel.compose.CursorWheelLayout
import com.cursorwheel.compose.ItemRotationMode

class ComposeWheelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWheelTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF13171C) // background_dark
                ) {
                    ComposeWheelDemo()
                }
            }
        }
    }
}

@Composable
fun ComposeWheelDemo() {
    val context = LocalContext.current

    val smallDataset = remember { (1..6).map { "Item $it" } }
    val mediumDataset = remember { (1..23).map { it.toString() } }
    val largeDataset = remember { (1..30).map { "#$it" } }

    var currentItems by remember { mutableStateOf(mediumDataset) }
    var selectedItem by remember { mutableStateOf(currentItems.first()) }
    var lastClickedItem by remember { mutableStateOf("") }

    LaunchedEffect(currentItems) {
        selectedItem = currentItems.first()
        lastClickedItem = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Compose Wheel Demo",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { currentItems = smallDataset }) {
                Text("6 Items")
            }

            Button(onClick = { currentItems = mediumDataset }) {
                Text("23 Items")
            }

            Button(onClick = { currentItems = largeDataset }) {
                Text("30 Items")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selected: $selectedItem",
            color = Color(0xFFFFFFFF),
            fontSize = 18.sp
        )

        if (lastClickedItem.isNotEmpty()) {
            Text(
                text = "Last clicked: $lastClickedItem",
                color = Color(0x4CFFFFFF),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        val wheelSize = when {
            currentItems.size <= 6 -> 240.dp
            currentItems.size <= 23 -> 280.dp
            else -> 320.dp
        }

        val itemSize = when {
            currentItems.size <= 6 -> 60.dp
            currentItems.size <= 23 -> 50.dp
            else -> 45.dp
        }

        CursorWheelLayout(
            items = currentItems,
            modifier = Modifier,
            wheelSize = wheelSize,
            itemSize = itemSize,
            selectedAngle = -90f,
            paddingRatio = 0.002f,
            wheelBackgroundColor = Color(0xE513171C),
            itemRotationMode = ItemRotationMode.None,
            onItemSelected = { _, item ->
                selectedItem = item
            },
            onItemClick = { _, item ->
                lastClickedItem = item
                Toast.makeText(
                    context,
                    "Clicked: $item",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) { _, item, isSelected ->
            WheelItem(
                text = item,
                isSelected = isSelected,
                itemCount = currentItems.size
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Drag to rotate • Tap to select • Fling to spin",
            color = Color(0x4CFFFFFF),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun WheelItem(
    text: String,
    isSelected: Boolean,
    itemCount: Int,
    modifier: Modifier = Modifier
) {
    val fontSize = when {
        itemCount <= 6 -> 18.sp
        itemCount <= 23 -> 16.sp
        else -> 14.sp
    }

    val itemSize = when {
        itemCount <= 6 -> 45.dp
        itemCount <= 23 -> 40.dp
        else -> 35.dp
    }

    Box(
        modifier = modifier
            .size(itemSize)
            .clip(CircleShape)
            .background(
                if (isSelected) {
                    Color(0xFFFFC52A)
                } else {
                    Color.Transparent
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) {
                Color(0xFF212121)
            } else {
                Color.White
            },
            fontSize = fontSize,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ComposeWheelTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeWheelDemoPreview() {
    ComposeWheelTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF13171C)
        ) {
            ComposeWheelDemo()
        }
    }
}