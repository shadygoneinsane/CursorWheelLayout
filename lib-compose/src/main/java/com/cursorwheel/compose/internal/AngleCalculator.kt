package com.cursorwheel.compose.internal

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

/**
 * Calculate angle from center point in degrees
 */
internal fun calculateAngle(x: Float, y: Float): Float {
    return (atan2(y, x) * 180.0 / PI).toFloat()
}

/**
 * Calculate the shortest rotation path to reach target item
 */
internal fun calculateShortestPath(
    currentAngle: Float,
    targetItemIndex: Int,
    angleStep: Float,
    itemCount: Int,
    selectedAngle: Float
): Float {
    if (itemCount == 0) return currentAngle

    // Calculate where the target item should be positioned to align with selectedAngle
    val targetAngle = selectedAngle - targetItemIndex * angleStep

    // Normalize both angles to 0-360 range for comparison
    val normalizedCurrent = ((currentAngle % 360f) + 360f) % 360f
    val normalizedTarget = ((targetAngle % 360f) + 360f) % 360f

    // Calculate the shortest angular distance
    val clockwiseDistance = if (normalizedTarget >= normalizedCurrent) {
        normalizedTarget - normalizedCurrent
    } else {
        normalizedTarget + 360f - normalizedCurrent
    }

    val counterClockwiseDistance = 360f - clockwiseDistance

    // Choose the shorter path and apply it to the current angle
    return if (clockwiseDistance <= counterClockwiseDistance) {
        currentAngle + clockwiseDistance
    } else {
        currentAngle - counterClockwiseDistance
    }
}

/**
 * Snap to the nearest item position
 */
internal suspend fun snapToNearestItem(
    animatedAngle: Animatable<Float, *>,
    currentAngle: Float,
    angleStep: Float,
    itemCount: Int,
    selectedAngle: Float = 0f
) {
    if (itemCount == 0) return

    // Find the nearest item that should align with selectedAngle
    val roundedSteps = ((currentAngle - selectedAngle) / angleStep).roundToInt()
    val targetAngle = selectedAngle + roundedSteps * angleStep

    animatedAngle.animateTo(
        targetValue = targetAngle,
        animationSpec = tween(durationMillis = 300)
    )
}