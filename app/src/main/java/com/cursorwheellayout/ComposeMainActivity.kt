package com.cursorwheellayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursorwheel.compose.CursorWheelAdapter
import com.cursorwheel.compose.CursorWheelLayout
import com.cursorwheel.compose.ItemRotationMode
import com.cursorwheel.compose.ListCursorWheelAdapter
import com.cursorwheel.demo.R
import kotlin.random.Random

/**
 * Compose-based replacement for MainActivity that replicates all the original functionality
 * using the enhanced CursorWheelCompose with full customization support
 */
class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0x4C3C4952) // Same background as original
                ) {
                    ComposeMainContent()
                }
            }
        }
    }
}

data class TextItem(val text: String)
data class ImageItem(val drawableRes: Int, val text: String)

@Composable
fun ComposeMainContent() {
    val context = LocalContext.current
    
    // Configuration for wheel backgrounds (make them configurable)
    val topWheelBackgroundColor = Color.Transparent // Remove gray background
    val leftWheelBackgroundColor = Color.Transparent // Remove gray background
    val rightWheelBackgroundColor = Color(0x7FFFC52A) // Keep translucent yellow
    
    // Random selection state
    var randomIndex by remember { mutableIntStateOf(0) }
    
    // Text data - same as original MainActivity
    val textItems = remember {
        listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "OFF")
            .map { TextItem(it) }
    }
    
    // Image data - same as original MainActivity  
    val imageItems = remember {
        listOf(
            ImageItem(R.drawable.ic_bank_bc, "0"),
            ImageItem(R.drawable.ic_bank_china, "1"),
            ImageItem(R.drawable.ic_bank_guangda, "2"),
            ImageItem(R.drawable.ic_bank_guangfa, "3"),
            ImageItem(R.drawable.ic_bank_jianshe, "4"),
            ImageItem(R.drawable.ic_bank_jiaotong, "5")
        )
    }
    
    // Create adapters
    val textAdapter = remember { ListCursorWheelAdapter(textItems) }
    val imageAdapter = remember { ListCursorWheelAdapter(imageItems) }

    Box(modifier = Modifier.fillMaxSize()) {
        
        // Left wheel (bottom-left corner, partially visible) - Same as test_circle_menu_left
        Box(
            modifier = Modifier
                .size(306.dp)
                .align(Alignment.CenterStart)
                .offset(x = (-153).dp, y = (-100).dp)
        ) {
            CursorWheelLayout(
                adapter = textAdapter,
                wheelSize = 306.dp,
                itemSize = 50.dp,
                selectedAngle = 0f, // Same as original
                itemRotationMode = ItemRotationMode.Outward, // Same as original
                wheelBackgroundColor = leftWheelBackgroundColor, // Configurable background
                onItemSelected = { index, item ->
                    // Same functionality as original
                },
                onItemClick = { index, item ->
                    Toast.makeText(context, "Left wheel: ${item.text}", Toast.LENGTH_SHORT).show()
                }
            ) { index, item, isSelected ->
                TextWheelItem(
                    text = item.text,
                    isSelected = isSelected,
                    gravity = "center" // Same as original
                )
            }
        }
        
        // Top wheel (center-top) - Same as test_circle_menu_top 
        Box(
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.TopCenter)
        ) {
            CursorWheelLayout(
                adapter = imageAdapter,
                wheelSize = 280.dp,
                itemSize = 60.dp,
                selectedAngle = 270f, // Same as original (top position)
                itemRotationMode = ItemRotationMode.None, // Same as original
                wheelBackgroundColor = topWheelBackgroundColor, // Configurable background
                cursorColor = Color(0xFF009688), // Same teal color as original
                cursorHeight = 19.dp, // Same as original
                onItemSelected = { index, item ->
                    Toast.makeText(
                        context,
                        "Top Menu selected position:$index",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onItemClick = { index, item ->
                    Toast.makeText(
                        context,
                        "Top Menu click position:$index",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) { index, item, isSelected ->
                ImageWheelItem(
                    imageRes = item.drawableRes,
                    isSelected = isSelected
                )
            }
        }
        
        // Right wheel (bottom-right corner, partially visible) - Same as test_circle_menu_right
        Box(
            modifier = Modifier
                .size(306.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 153.dp, y = 153.dp)
        ) {
            CursorWheelLayout(
                adapter = textAdapter,
                wheelSize = 306.dp,
                itemSize = 50.dp,
                selectedAngle = 225f, // Same as original
                wheelBackgroundColor = rightWheelBackgroundColor, // Configurable background
                cursorColor = Color.Red, // Same as original
                cursorHeight = 20.dp, // Same as original
                flingThreshold = 460f, // Same as original
                itemRotationMode = ItemRotationMode.Inward, // Same as original
                onItemSelected = { index, item ->
                    // Same functionality as original
                },
                onItemClick = { index, item ->
                    Toast.makeText(context, "Right wheel: ${item.text}", Toast.LENGTH_SHORT).show()
                }
            ) { index, item, isSelected ->
                TextWheelItem(
                    text = item.text,
                    isSelected = isSelected,
                    gravity = "center" // Same as original
                )
            }
        }
        
        // Random selection button (same position as original)
        Button(
            onClick = {
                randomIndex = Random.nextInt(10)
                Toast.makeText(
                    context,
                    "Random Selected: $randomIndex",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = if (randomIndex == 0) "Random Selected" else "Random Selected: $randomIndex",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun TextWheelItem(
    text: String,
    isSelected: Boolean,
    gravity: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(
                if (isSelected) Color(0xFFFFC52A) else Color.Transparent
            ),
        contentAlignment = when (gravity) {
            "top_center" -> Alignment.TopCenter
            "bottom_center" -> Alignment.BottomCenter
            else -> Alignment.Center
        }
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.Black else Color.White,
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ImageWheelItem(
    imageRes: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(55.dp)
            .clip(CircleShape)
            .background(
                if (isSelected) Color(0xFFFFC52A) else Color(0x30FFFFFF)
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}