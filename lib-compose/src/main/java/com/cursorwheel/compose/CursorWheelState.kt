package com.cursorwheel.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cursorwheel.compose.internal.WheelConstants
import com.cursorwheel.compose.internal.calculateShortestPath

@Composable
fun rememberCursorWheelState(): CursorWheelState {
    return remember { CursorWheelState() }
}

class CursorWheelState {
    val angle = Animatable(0f)
    var selectedIndex by mutableIntStateOf(0)

    suspend fun selectItem(targetIndex: Int, itemCount: Int, angleStep: Float, selectedAngle: Float) {
        if (itemCount == 0 || targetIndex < 0 || targetIndex >= itemCount) return

        val targetAngle = calculateShortestPath(
            currentAngle = angle.value,
            targetItemIndex = targetIndex,
            angleStep = angleStep,
            itemCount = itemCount,
            selectedAngle = selectedAngle
        )
        angle.animateTo(
            targetValue = targetAngle,
            animationSpec = tween(durationMillis = WheelConstants.SNAP_ANIMATION_DURATION_MS)
        )
    }
}