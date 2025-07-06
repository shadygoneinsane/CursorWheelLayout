package com.cursorwheel.compose.internal

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Constants for CursorWheelLayout
internal object WheelConstants {
    const val DEFAULT_WHEEL_SIZE_DP = 300
    const val DEFAULT_ITEM_SIZE_DP = 60
    const val DEFAULT_PADDING_RATIO = 0.083f // 1/12 default padding
    const val DEFAULT_FLING_THRESHOLD = 300f // degrees per second
    const val DEFAULT_SELECTED_ANGLE = 0f

    // Touch and gesture constants
    const val MIN_CENTER_DISTANCE = 50f // Minimum distance from center for touch
    const val CLICK_THRESHOLD_DEGREES = 5f // Maximum movement for click detection
    const val ROTATION_THRESHOLD_DEGREES = 5f // Minimum rotation to consider as non-click

    // Animation constants
    const val SNAP_ANIMATION_DURATION_MS = 300
    const val FRICTION_MULTIPLIER = 2f
    const val VELOCITY_THRESHOLD = 20f

    // Angle calculation constants
    const val FULL_CIRCLE_DEGREES = 360f
    const val HALF_CIRCLE_DEGREES = 180f
}