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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.cursorwheel.compose.MutableCursorWheelAdapter
import com.cursorwheel.demo.R
import kotlin.random.Random

/**
 * Advanced example demonstrating all the flexible APIs of CursorWheelCompose
 */
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
    
    // Different demo modes
    var currentDemo by remember { mutableStateOf("Dynamic Adapter") }
    var selectedItem by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Advanced Wheel APIs",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Demo selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { currentDemo = "Dynamic Adapter" }
            ) {
                Text("Dynamic", fontSize = 9.sp)
            }
            
            Button(
                onClick = { currentDemo = "Item Provider" }
            ) {
                Text("Provider", fontSize = 9.sp)
            }
            
            Button(
                onClick = { currentDemo = "Icon Demo" }
            ) {
                Text("Icons", fontSize = 9.sp)
            }
            
            Button(
                onClick = { currentDemo = "Custom Adapter" }
            ) {
                Text("Custom", fontSize = 9.sp)
            }
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
        
        // Display appropriate demo
        when (currentDemo) {
            "Dynamic Adapter" -> DynamicAdapterDemo(
                onItemSelected = { selectedItem = it },
                onItemClick = { item ->
                    Toast.makeText(context, "Dynamic: $item", Toast.LENGTH_SHORT).show()
                }
            )
            
            "Item Provider" -> ItemProviderDemo(
                onItemSelected = { selectedItem = it },
                onItemClick = { item ->
                    Toast.makeText(context, "Provider: $item", Toast.LENGTH_SHORT).show()
                }
            )
            
            "Icon Demo" -> IconDemo(
                onItemSelected = { selectedItem = it },
                onItemClick = { item ->
                    Toast.makeText(context, "Icon: $item", Toast.LENGTH_SHORT).show()
                }
            )
            
            "Custom Adapter" -> CustomAdapterDemo(
                onItemSelected = { selectedItem = it },
                onItemClick = { item ->
                    Toast.makeText(context, "Custom: $item", Toast.LENGTH_SHORT).show()
                }
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = when (currentDemo) {
                "Dynamic Adapter" -> "Add/remove items dynamically • MutableCursorWheelAdapter"
                "Item Provider" -> "Generate items on-demand • itemProvider function"
                "Icon Demo" -> "Drawable icons with colors • ic_sample_* resources"
                "Custom Adapter" -> "Complex data types • Custom CursorWheelAdapter"
                else -> ""
            },
            color = Color(0x4CFFFFFF),
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DynamicAdapterDemo(
    onItemSelected: (String) -> Unit,
    onItemClick: (String) -> Unit
) {
    // Mutable adapter that can be modified
    val adapter = remember { 
        MutableCursorWheelAdapter(mutableListOf("A", "B", "C", "D", "E"))
    }
    var itemCount by remember { mutableIntStateOf(adapter.getCount()) }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Controls
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    val newItem = ('A'..'Z').random().toString()
                    adapter.addItem(newItem)
                    itemCount = adapter.getCount()
                }
            ) {
                Text("Add", fontSize = 12.sp)
            }
            
            Button(
                onClick = {
                    if (adapter.getCount() > 1) {
                        adapter.removeItem(adapter.getCount() - 1)
                        itemCount = adapter.getCount()
                    }
                }
            ) {
                Text("Remove", fontSize = 12.sp)
            }
            
            Button(
                onClick = {
                    adapter.clear()
                    repeat(Random.nextInt(3, 8)) {
                        adapter.addItem(('A'..'Z').random().toString())
                    }
                    itemCount = adapter.getCount()
                }
            ) {
                Text("Shuffle", fontSize = 12.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Wheel with dynamic adapter
        CursorWheelLayout(
            adapter = adapter,
            wheelSize = 260.dp,
            itemSize = 50.dp,
            selectedAngle = -90f,
            onItemSelected = { _, item -> onItemSelected(item) },
            onItemClick = { _, item -> onItemClick(item) }
        ) { _, item, isSelected ->
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color(0xFFFFC52A) else Color(0x30FFFFFF)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item,
                    color = if (isSelected) Color.Black else Color.White,
                    fontSize = 16.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Items: $itemCount",
            color = Color(0x4CFFFFFF),
            fontSize = 12.sp
        )
    }
}

@Composable
fun ItemProviderDemo(
    onItemSelected: (String) -> Unit,
    onItemClick: (String) -> Unit
) {
    var itemCount by remember { mutableIntStateOf(8) }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Controls
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { if (itemCount > 3) itemCount-- }
            ) {
                Text("-", fontSize = 16.sp)
            }
            
            Text(
                text = "$itemCount items",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            Button(
                onClick = { if (itemCount < 20) itemCount++ }
            ) {
                Text("+", fontSize = 16.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Wheel with item provider function
        CursorWheelLayout(
            itemCount = itemCount,
            itemProvider = { index -> "Item ${index + 1}" },
            wheelSize = 280.dp,
            itemSize = 45.dp,
            selectedAngle = -90f,
            onItemSelected = { _, item -> onItemSelected(item) },
            onItemClick = { _, item -> onItemClick(item) }
        ) { index, item, isSelected ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (isSelected) Color(0xFF4CAF50) else Color(0x40FFFFFF)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${index + 1}",
                    color = if (isSelected) Color.White else Color(0xCCFFFFFF),
                    fontSize = 14.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

data class UserItem(
    val id: Int,
    val name: String,
    val role: String,
    val isOnline: Boolean
)

data class IconItem(
    val id: Int,
    val drawableRes: Int,
    val name: String,
    val color: Color = Color.White
)

@Composable
fun IconDemo(
    onItemSelected: (String) -> Unit,
    onItemClick: (String) -> Unit
) {
    // Icon adapter using ic_sample drawable resources
    val iconAdapter = remember {
        object : CursorWheelAdapter<IconItem> {
            private val icons = listOf(
                IconItem(1, R.drawable.ic_sample_1, "Card", Color(0xFF2196F3)),
                IconItem(2, R.drawable.ic_sample_2, "User", Color(0xFF4CAF50)),
                IconItem(3, R.drawable.ic_sample_3, "Home", Color(0xFFFF9800)),
                IconItem(4, R.drawable.ic_sample_4, "Star", Color(0xFFF44336))
            )
            
            override fun getCount(): Int = icons.size
            override fun getItem(position: Int): IconItem = icons[position]
        }
    }
    
    var rotationMode by remember { mutableStateOf(ItemRotationMode.None) }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Rotation mode controls
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { rotationMode = ItemRotationMode.None }
            ) {
                Text("None", fontSize = 11.sp)
            }
            
            Button(
                onClick = { rotationMode = ItemRotationMode.Inward }
            ) {
                Text("Inward", fontSize = 11.sp)
            }
            
            Button(
                onClick = { rotationMode = ItemRotationMode.Outward }
            ) {
                Text("Outward", fontSize = 11.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Wheel with icon adapter
        CursorWheelLayout(
            adapter = iconAdapter,
            wheelSize = 280.dp,
            itemSize = 60.dp,
            selectedAngle = -90f,
            itemRotationMode = rotationMode,
            onItemSelected = { _, icon -> onItemSelected(icon.name) },
            onItemClick = { _, icon -> onItemClick(icon.name) }
        ) { _, icon, isSelected ->
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color(0xFFFFC52A) else Color(0x20FFFFFF)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon.drawableRes),
                    contentDescription = icon.name,
                    modifier = Modifier.size(32.dp),
                    tint = if (isSelected) Color.Black else icon.color
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Rotation: ${rotationMode.name}",
            color = Color(0x4CFFFFFF),
            fontSize = 12.sp
        )
    }
}

@Composable
fun CustomAdapterDemo(
    onItemSelected: (String) -> Unit,
    onItemClick: (String) -> Unit
) {
    // Custom adapter with complex data
    val customAdapter = remember {
        object : CursorWheelAdapter<UserItem> {
            private val users = listOf(
                UserItem(1, "Alice", "Admin", true),
                UserItem(2, "Bob", "User", false),
                UserItem(3, "Carol", "Mod", true),
                UserItem(4, "Dave", "User", true),
                UserItem(5, "Eve", "Admin", false),
                UserItem(6, "Frank", "User", true)
            )
            
            override fun getCount(): Int = users.size
            override fun getItem(position: Int): UserItem = users[position]
            override fun getItemViewType(position: Int): Int = if (users[position].role == "Admin") 1 else 0
        }
    }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Wheel with custom adapter
        CursorWheelLayout(
            adapter = customAdapter,
            wheelSize = 300.dp,
            itemSize = 55.dp,
            selectedAngle = -90f,
            itemRotationMode = ItemRotationMode.Inward,
            onItemSelected = { _, user -> onItemSelected("${user.name} (${user.role})") },
            onItemClick = { _, user -> onItemClick("${user.name} - ${user.role}") }
        ) { _, user, isSelected ->
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = user.name.first().toString(),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
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
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Blue: Admin • Purple: Mod • Gray: User • Green dot: Online",
            color = Color(0x4CFFFFFF),
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AdvancedWheelTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}