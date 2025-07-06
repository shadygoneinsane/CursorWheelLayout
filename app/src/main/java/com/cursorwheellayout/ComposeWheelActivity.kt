package com.cursorwheellayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    // Different test datasets
    val smallDataset = remember { (1..6).map { "Item $it" } }
    val mediumDataset = remember { (1..23).map { it.toString() } }
    val largeDataset = remember { (1..30).map { "#$it" } }

    // Current mode state
    var currentMode by remember { mutableStateOf("Medium (23 items)") }
    var currentItems by remember { mutableStateOf(mediumDataset) }
    var selectedItem by remember { mutableStateOf(currentItems.first()) }
    var lastClickedItem by remember { mutableStateOf("") }

    // Track when dataset changes to reset wheel position
    var datasetChangeKey by remember { mutableStateOf(0) }

    // Update selected item when dataset changes
    LaunchedEffect(currentItems) {
        selectedItem = currentItems.first()
        lastClickedItem = ""
        datasetChangeKey += 1 // Trigger wheel reset
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Compose Wheel Demo",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mode selector buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    currentMode = "Small (6 items)"
                    currentItems = smallDataset
                }
            ) {
                Text("6 Items")
            }

            Button(
                onClick = {
                    currentMode = "Medium (23 items)"
                    currentItems = mediumDataset
                }
            ) {
                Text("23 Items")
            }

            Button(
                onClick = {
                    currentMode = "Large (30 items)"
                    currentItems = largeDataset
                }
            ) {
                Text("30 Items")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Current mode info
        Text(
            text = "Mode: $currentMode",
            color = Color(0x4CFFFFFF),
            fontSize = 14.sp
        )

        // Selection info
        Text(
            text = "Selected: $selectedItem",
            color = Color(0xFFFFFFFF),
            fontSize = 18.sp
        )

        if (lastClickedItem.isNotEmpty()) {
            Text(
                text = "Last clicked: $lastClickedItem",
                color = Color(0x4CFFFFFF), // secondary_text_light
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Cursor Wheel Layout with dynamic sizing based on item count
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

        // Use key to reset wheel when dataset changes
        key(datasetChangeKey) {
            CursorWheelLayout(
                items = currentItems,
                modifier = Modifier,
                wheelSize = wheelSize,
                itemSize = itemSize,
                selectedAngle = -90f, // Top position (-90° = 12 o'clock position)
                paddingRatio = 0.002f,
                wheelBackgroundColor = Color(0xE513171C), // bg_wheel
                itemRotationMode = ItemRotationMode.None,
                onItemSelected = { index, item ->
                    selectedItem = item
                },
                onItemClick = { index, item ->
                    lastClickedItem = item
                    Toast.makeText(
                        context,
                        "Clicked: $item (${currentItems.size} items)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) { index, item, isSelected ->
                WheelItem(
                    text = item,
                    isSelected = isSelected,
                    itemCount = currentItems.size
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Description
        Text(
            text = "Drag to rotate • Tap to select • Fling to spin",
            color = Color(0x4CFFFFFF),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Test different item counts to see wheel adaptation",
            color = Color(0x33FFFFFF),
            fontSize = 10.sp,
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
    // Adjust text size based on item count for better readability
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
                    Color(0xFFFFC52A) // colorAccent
                } else {
                    Color.Transparent // No background for non-selected items
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) {
                Color(0xFF212121) // primary_text_dark
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