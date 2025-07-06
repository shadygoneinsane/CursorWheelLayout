package com.cursorwheellayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import com.cursorwheel.demo.R
import kotlin.random.Random

class AdvancedComposeWheelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedWheelTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF13171C)
                ) {
                    AdvancedWheelDemo()
                }
            }
        }
    }
}

@Composable
fun AdvancedWheelDemo() {
    val context = LocalContext.current
    var currentDemo by remember { mutableStateOf("Dynamic") }
    var selectedItem by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Advanced Wheel APIs",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { currentDemo = "Dynamic" }) { Text("Dynamic", fontSize = 9.sp) }
            Button(onClick = { currentDemo = "Provider" }) { Text("Provider", fontSize = 9.sp) }
            Button(onClick = { currentDemo = "Icon" }) { Text("Icons", fontSize = 9.sp) }
            Button(onClick = { currentDemo = "Custom" }) { Text("Custom", fontSize = 9.sp) }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Demo: $currentDemo",
            color = Color(0x4CFFFFFF),
            fontSize = 14.sp
        )

        if (selectedItem.isNotEmpty()) {
            Text(
                text = "Selected: $selectedItem",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (currentDemo) {
            "Dynamic" -> DynamicDemo(
                onItemSelected = { selectedItem = it },
                onItemClick = { item ->
                    Toast.makeText(context, "Dynamic: $item", Toast.LENGTH_SHORT).show()
                }
            )
            "Provider" -> ProviderDemo(
                onItemSelected = { selectedItem = it },
                onItemClick = { item ->
                    Toast.makeText(context, "Provider: $item", Toast.LENGTH_SHORT).show()
                }
            )
            "Icon" -> IconDemo(
                onItemSelected = { selectedItem = it },
                onItemClick = { item ->
                    Toast.makeText(context, "Icon: $item", Toast.LENGTH_SHORT).show()
                }
            )
            "Custom" -> CustomDemo(
                onItemSelected = { selectedItem = it },
                onItemClick = { item ->
                    Toast.makeText(context, "Custom: $item", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun DynamicDemo(onItemSelected: (String) -> Unit, onItemClick: (String) -> Unit) {
    var items by remember { mutableStateOf(listOf("A", "B", "C", "D", "E")) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { items = items + ('A'..'Z').random().toString() }) { Text("Add") }
            Button(onClick = { if (items.size > 1) items = items.dropLast(1) }) { Text("Remove") }
            Button(onClick = { items = items.shuffled() }) { Text("Shuffle") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CursorWheelLayout(
            items = items,
            wheelSize = 260.dp,
            itemSize = 50.dp,
            selectedAngle = -90f,
            onItemSelected = { _, item -> onItemSelected(item) },
            onItemClick = { _, item -> onItemClick(item) }
        ) { _, item, isSelected ->
            TextWheelItem(text = item, isSelected = isSelected)
        }
    }
}

@Composable
fun ProviderDemo(onItemSelected: (String) -> Unit, onItemClick: (String) -> Unit) {
    var itemCount by remember { mutableIntStateOf(8) }
    val items = remember(itemCount) { (1..itemCount).map { "Item $it" } }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { if (itemCount > 3) itemCount-- }) { Text("-") }
            Text("$itemCount items", color = Color.White, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            Button(onClick = { if (itemCount < 20) itemCount++ }) { Text("+") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CursorWheelLayout(
            items = items,
            wheelSize = 280.dp,
            itemSize = 45.dp,
            selectedAngle = -90f,
            onItemSelected = { _, item -> onItemSelected(item) },
            onItemClick = { _, item -> onItemClick(item) }
        ) { index, _, isSelected ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) Color(0xFF4CAF50) else Color(0x40FFFFFF)),
                contentAlignment = Alignment.Center
            ) {
                Text("${index + 1}", color = if (isSelected) Color.White else Color(0xCCFFFFFF))
            }
        }
    }
}

@Composable
fun IconDemo(onItemSelected: (String) -> Unit, onItemClick: (String) -> Unit) {
    val items = remember {
        listOf(
            IconItem(1, R.drawable.ic_sample_1, "Card", Color(0xFF2196F3)),
            IconItem(2, R.drawable.ic_sample_2, "User", Color(0xFF4CAF50)),
            IconItem(3, R.drawable.ic_sample_3, "Home", Color(0xFFFF9800)),
            IconItem(4, R.drawable.ic_sample_4, "Star", Color(0xFFF44336))
        )
    }
    var rotationMode by remember { mutableStateOf(ItemRotationMode.None) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { rotationMode = ItemRotationMode.None }) { Text("None") }
            Button(onClick = { rotationMode = ItemRotationMode.Inward }) { Text("Inward") }
            Button(onClick = { rotationMode = ItemRotationMode.Outward }) { Text("Outward") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CursorWheelLayout(
            items = items,
            wheelSize = 280.dp,
            itemSize = 60.dp,
            selectedAngle = -90f,
            itemRotationMode = rotationMode,
            onItemSelected = { _, item -> onItemSelected(item.name) },
            onItemClick = { _, item -> onItemClick(item.name) }
        ) { _, item, isSelected ->
            IconWheelItem(item = item, isSelected = isSelected)
        }
    }
}

@Composable
fun CustomDemo(onItemSelected: (String) -> Unit, onItemClick: (String) -> Unit) {
    val items = remember {
        listOf(
            UserItem(1, "Alice", "Admin", true),
            UserItem(2, "Bob", "User", false),
            UserItem(3, "Carol", "Mod", true),
            UserItem(4, "Dave", "User", true),
            UserItem(5, "Eve", "Admin", false),
            UserItem(6, "Frank", "User", true)
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CursorWheelLayout(
            items = items,
            wheelSize = 300.dp,
            itemSize = 55.dp,
            selectedAngle = -90f,
            itemRotationMode = ItemRotationMode.Inward,
            onItemSelected = { _, user -> onItemSelected("${user.name} (${user.role})") },
            onItemClick = { _, user -> onItemClick("${user.name} - ${user.role}") }
        ) { _, user, isSelected ->
            UserWheelItem(user = user, isSelected = isSelected)
        }
    }
}

@Composable
fun TextWheelItem(text: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xFFFFC52A) else Color(0x30FFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = if (isSelected) Color.Black else Color.White)
    }
}

@Composable
fun IconWheelItem(item: IconItem, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(55.dp)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xFFFFC52A) else Color(0x20FFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = item.drawableRes),
            contentDescription = item.name,
            modifier = Modifier.size(32.dp),
            tint = if (isSelected) Color.Black else item.color
        )
    }
}

@Composable
fun UserWheelItem(user: UserItem, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(
                when {
                    isSelected -> Color(0xFFFFC52A)
                    user.role == "Admin" -> Color(0xFF2196F3)
                    user.role == "Mod" -> Color(0xFF9C27B0)
                    else -> Color(0xFF607D8B)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = user.name.first().toString(), color = Color.White, fontWeight = FontWeight.Bold)
            if (user.isOnline) {
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .clip(CircleShape)
                        .background(Color.Green)
                )
            }
        }
    }
}

data class IconItem(val id: Int, val drawableRes: Int, val name: String, val color: Color)
data class UserItem(val id: Int, val name: String, val role: String, val isOnline: Boolean)

@Composable
fun AdvancedWheelTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}