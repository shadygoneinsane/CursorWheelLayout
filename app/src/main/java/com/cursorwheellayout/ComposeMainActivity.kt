package com.cursorwheellayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursorwheel.compose.CursorWheelLayout
import com.cursorwheel.compose.ItemRotationMode
import com.cursorwheel.compose.rememberCursorWheelState
import com.cursorwheel.demo.R
import kotlin.random.Random

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

    val topWheelBackgroundColor = Color.Gray.copy(alpha = 0.3f)
    val leftWheelBackgroundColor = Color.Gray.copy(alpha = 0.3f)
    val rightWheelBackgroundColor = Color(0x7FFFC52A)

    var randomIndex by remember { mutableIntStateOf(0) }

    val textItems = remember {
        listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "OFF")
            .map { TextItem(it) }
    }

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

    val leftWheelState = rememberCursorWheelState()
    val topWheelState = rememberCursorWheelState()
    val rightWheelState = rememberCursorWheelState()

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(306.dp)
                .align(Alignment.CenterStart)
                .offset(x = (-153).dp, y = (0).dp)
        ) {
            CursorWheelLayout(
                items = textItems,
                state = leftWheelState,
                wheelSize = 306.dp,
                itemSize = 50.dp,
                selectedAngle = 0f,
                itemRotationMode = ItemRotationMode.Outward,
                wheelBackgroundColor = leftWheelBackgroundColor,
                onItemClick = { _, item ->
                    Toast.makeText(context, "Left wheel: ${item.text}", Toast.LENGTH_SHORT).show()
                }
            ) { _, item, isSelected ->
                TextWheelItem(
                    text = item.text,
                    isSelected = isSelected,
                    gravity = "center"
                )
            }
        }

        Box(
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.TopCenter)
        ) {
            CursorWheelLayout(
                items = imageItems,
                state = topWheelState,
                wheelSize = 280.dp,
                itemSize = 60.dp,
                selectedAngle = 270f,
                itemRotationMode = ItemRotationMode.None,
                wheelBackgroundColor = topWheelBackgroundColor,
                cursorColor = Color(0xFF009688),
                cursorHeight = 19.dp,
                onItemSelected = { index, _ ->
                    Toast.makeText(
                        context,
                        "Top Menu selected position:$index",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onItemClick = { index, _ ->
                    Toast.makeText(
                        context,
                        "Top Menu click position:$index",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) { _, item, isSelected ->
                ImageWheelItem(
                    imageRes = item.drawableRes,
                    isSelected = isSelected
                )
            }
        }

        Box(
            modifier = Modifier
                .size(306.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 153.dp, y = 153.dp)
        ) {
            CursorWheelLayout(
                items = textItems,
                state = rightWheelState,
                wheelSize = 306.dp,
                itemSize = 50.dp,
                selectedAngle = 225f,
                wheelBackgroundColor = rightWheelBackgroundColor,
                cursorColor = Color.Red,
                cursorHeight = 20.dp,
                flingThreshold = 460f,
                itemRotationMode = ItemRotationMode.Inward,
                onItemClick = { _, item ->
                    Toast.makeText(context, "Right wheel: ${item.text}", Toast.LENGTH_SHORT).show()
                }
            ) { _, item, isSelected ->
                TextWheelItem(
                    text = item.text,
                    isSelected = isSelected,
                    gravity = "center"
                )
            }
        }

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