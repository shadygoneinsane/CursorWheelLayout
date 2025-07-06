package com.cursorwheel.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cursorwheel.compose.internal.WheelConstants
import com.cursorwheel.compose.internal.calculateAngle
import com.cursorwheel.compose.internal.calculateShortestPath
import com.cursorwheel.compose.internal.drawCursor
import com.cursorwheel.compose.internal.drawGuideLines
import com.cursorwheel.compose.internal.snapToNearestItem
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * Jetpack Compose implementation of CursorWheelLayout
 *
 * A circular wheel layout where items are positioned in a circle and can be rotated via touch gestures.
 * This is the main composable that supports custom item rendering.
 *
 * @param T The type of the items in the list.
 * @param items The list of items to display in the wheel.
 * @param modifier The modifier to apply to this layout.
 * @param wheelSize The size of the wheel.
 * @param itemSize The size of each item in the wheel.
 * @param selectedAngle The angle at which the selected item is positioned.
 * @param paddingRatio The ratio of padding to the wheel size.
 * @param wheelBackgroundColor The background color of the wheel.
 * @param itemRotationMode The rotation mode for the items.
 * @param flingThreshold The threshold for fling gestures.
 * @param cursorColor The color of the cursor.
 * @param cursorHeight The height of the cursor.
 * @param guideLineWidth The width of the guide lines.
 * @param guideLineColor The color of the guide lines.
 * @param onItemSelected A callback that is invoked when an item is selected.
 * @param onItemClick A callback that is invoked when an item is clicked.
 * @param itemContent A composable that renders each item in the wheel.
 */
@Composable
fun <T> CursorWheelLayout(
    items: List<T>,
    modifier: Modifier = Modifier,
    wheelSize: Dp = WheelConstants.DEFAULT_WHEEL_SIZE_DP.dp,
    itemSize: Dp = WheelConstants.DEFAULT_ITEM_SIZE_DP.dp,
    selectedAngle: Float = WheelConstants.DEFAULT_SELECTED_ANGLE,
    paddingRatio: Float = WheelConstants.DEFAULT_PADDING_RATIO,
    wheelBackgroundColor: Color = Color.Gray.copy(alpha = 0.3f),
    itemRotationMode: ItemRotationMode = ItemRotationMode.None,
    flingThreshold: Float = WheelConstants.DEFAULT_FLING_THRESHOLD,
    cursorColor: Color = Color.Transparent,
    cursorHeight: Dp = 20.dp,
    guideLineWidth: Dp = 0.dp,
    guideLineColor: Color = Color.Transparent,
    onItemSelected: (index: Int, item: T) -> Unit = { _, _ -> },
    onItemClick: (index: Int, item: T) -> Unit = { _, _ -> },
    itemContent: @Composable (index: Int, item: T, isSelected: Boolean) -> Unit
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    // State variables
    var startAngle by remember { mutableFloatStateOf(0f) }
        var selectedIndex by remember { mutableIntStateOf(0) }
    var downTime by remember { mutableFloatStateOf(0f) }
    var totalRotation by remember { mutableFloatStateOf(0f) }

    // Animation state - initialize to selectedAngle so first item aligns properly
    val animatedAngle = remember { Animatable(selectedAngle) }

    // Derived values
    val itemCount = items.size
    val angleStep = if (itemCount > 0) WheelConstants.FULL_CIRCLE_DEGREES / itemCount else 0f
    val wheelSizePx = with(density) { wheelSize.toPx() }
    val itemSizePx = with(density) { itemSize.toPx() }
    val paddingPx = wheelSizePx * paddingRatio
    val layoutRadius = (wheelSizePx - itemSizePx) / 2f - paddingPx
    val wheelRadius = wheelSizePx / 2f

    // Update start angle when animation changes
    LaunchedEffect(animatedAngle.value) {
        startAngle = animatedAngle.value
    }

    // Calculate selected item based on current angle
    LaunchedEffect(startAngle, items.size) {
        if (items.isNotEmpty()) {
            // Find which item is closest to the selectedAngle (cursor position)
            var minAngleDiff = Float.MAX_VALUE
            var newSelectedIndex = 0

            for (i in 0 until itemCount) {
                val itemAngle = startAngle + i * angleStep
                // Normalize angles to 0-360 range
                val normalizedItemAngle = ((itemAngle % WheelConstants.FULL_CIRCLE_DEGREES) + WheelConstants.FULL_CIRCLE_DEGREES) % WheelConstants.FULL_CIRCLE_DEGREES
                val normalizedSelectedAngle = ((selectedAngle % WheelConstants.FULL_CIRCLE_DEGREES) + WheelConstants.FULL_CIRCLE_DEGREES) % WheelConstants.FULL_CIRCLE_DEGREES

                // Calculate shortest angular distance
                var angleDiff = abs(normalizedSelectedAngle - normalizedItemAngle)
                if (angleDiff > WheelConstants.HALF_CIRCLE_DEGREES) angleDiff = WheelConstants.FULL_CIRCLE_DEGREES - angleDiff

                if (angleDiff < minAngleDiff) {
                    minAngleDiff = angleDiff
                    newSelectedIndex = i
                }
            }

            if (newSelectedIndex != selectedIndex) {
                selectedIndex = newSelectedIndex
                onItemSelected(selectedIndex, items[selectedIndex])
            }
        }
    }

    Box(
        modifier = modifier.size(wheelSize),
        contentAlignment = Alignment.Center
    ) {
        // Wheel background and decorations
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Draw wheel background
            drawCircle(
                color = wheelBackgroundColor,
                radius = wheelSizePx / 2f,
                center = center
            )

            // Draw cursor if specified
            if (cursorColor != Color.Transparent) {
                drawCursor(
                    centerX = center.x,
                    centerY = center.y,
                    radius = wheelSizePx / 2f,
                    selectedAngle = selectedAngle,
                    cursorColor = cursorColor,
                    cursorHeight = with(density) { cursorHeight.toPx() }
                )
            }

            // Draw guide lines if specified
            if (guideLineWidth > 0.dp && guideLineColor != Color.Transparent) {
                drawGuideLines(
                    centerX = center.x,
                    centerY = center.y,
                    radius = wheelSizePx / 2f,
                    itemCount = itemCount,
                    startAngle = startAngle,
                    guideLineColor = guideLineColor,
                    guideLineWidth = with(density) { guideLineWidth.toPx() }
                )
            }
        }

        // Items layout with custom gesture handling
        Layout(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(items.size) {
                    awaitEachGesture {
                        val downEvent = awaitFirstDown()
                        val downPosition = downEvent.position
                        val centerX = size.width / 2f
                        val centerY = size.height / 2f

                        // Check if touch is within wheel bounds
                        val distance = hypot(
                            downPosition.x - centerX,
                            downPosition.y - centerY
                        )

                        // Only handle touches within the wheel and outside the center
                        if (distance > wheelRadius || distance < WheelConstants.MIN_CENTER_DISTANCE) return@awaitEachGesture

                        downTime = System.currentTimeMillis().toFloat()
                        totalRotation = 0f

                        var lastAngle = calculateAngle(
                            downPosition.x - centerX,
                            downPosition.y - centerY
                        )

                        // Check if it's a tap on an item for click handling
                        var isClick = true
                        var clickedItemIndex = -1

                        // Find which item was clicked
                        for (i in 0 until itemCount) {
                            val itemAngle = startAngle + i * angleStep
                            val itemAngleRad = Math.toRadians(itemAngle.toDouble())
                            val itemX = centerX + (layoutRadius * cos(itemAngleRad)).toFloat()
                            val itemY = centerY + (layoutRadius * sin(itemAngleRad)).toFloat()

                            val itemDistance = hypot(
                                downPosition.x - itemX,
                                downPosition.y - itemY
                            )

                            if (itemDistance <= itemSizePx / 2f) {
                                clickedItemIndex = i
                                break
                            }
                        }

                        // Handle drag
                        do {
                            val event = awaitPointerEvent()
                            val change = event.changes.first()

                            if (change.pressed) {
                                val currentPosition = change.position
                                val currentAngle = calculateAngle(
                                    currentPosition.x - centerX,
                                    currentPosition.y - centerY
                                )

                                // Calculate angle difference with proper wrapping
                                var deltaAngle = currentAngle - lastAngle
                                if (deltaAngle > WheelConstants.HALF_CIRCLE_DEGREES) deltaAngle -= WheelConstants.FULL_CIRCLE_DEGREES
                                if (deltaAngle < -WheelConstants.HALF_CIRCLE_DEGREES) deltaAngle += WheelConstants.FULL_CIRCLE_DEGREES

                                // If we've moved significantly, it's not a click
                                if (abs(deltaAngle) > WheelConstants.CLICK_THRESHOLD_DEGREES) {
                                    isClick = false
                                }

                                totalRotation += deltaAngle

                                scope.launch {
                                    animatedAngle.snapTo(animatedAngle.value + deltaAngle)
                                }

                                lastAngle = currentAngle
                                change.consume()
                            }
                        } while (event.changes.any { it.pressed })


                        // Handle click if it was a tap on an item
                        if (isClick && clickedItemIndex >= 0 && abs(totalRotation) < WheelConstants.ROTATION_THRESHOLD_DEGREES) {
                            onItemClick(clickedItemIndex, items[clickedItemIndex])
                            // Animate to clicked item using shortest rotation path
                            scope.launch {
                                val targetAngle = calculateShortestPath(
                                    currentAngle = animatedAngle.value,
                                    targetItemIndex = clickedItemIndex,
                                    angleStep = angleStep,
                                    itemCount = itemCount,
                                    selectedAngle = selectedAngle
                                )
                                animatedAngle.animateTo(
                                    targetValue = targetAngle,
                                    animationSpec = tween(durationMillis = WheelConstants.SNAP_ANIMATION_DURATION_MS)
                                )
                            }
                        } else {
                            // Handle fling or snap for drag gestures
                            val dragDuration = System.currentTimeMillis() - downTime
                            val velocity = if (dragDuration > 0) {
                                totalRotation / dragDuration * 1000f // degrees per second
                            } else 0f

                            scope.launch {
                                if (abs(velocity) > flingThreshold) {
                                    // Start fling animation with decay
                                    val decaySpec = exponentialDecay<Float>(
                                        frictionMultiplier = WheelConstants.FRICTION_MULTIPLIER,
                                        absVelocityThreshold = WheelConstants.VELOCITY_THRESHOLD
                                    )

                                    animatedAngle.animateDecay(
                                        initialVelocity = velocity,
                                        animationSpec = decaySpec
                                    )
                                }

                                // Snap to nearest item
                                snapToNearestItem(animatedAngle, animatedAngle.value, angleStep, itemCount, selectedAngle)
                            }
                        }
                    }
                },
            content = {
                items.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier.size(itemSize),
                        contentAlignment = Alignment.Center
                    ) {
                        itemContent(index, item, index == selectedIndex)
                    }
                }
            }
        ) { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }

            layout(constraints.maxWidth, constraints.maxHeight) {
                placeables.forEachIndexed { index, placeable ->
                    // Calculate angle for each item
                    val itemAngle = startAngle + index * angleStep
                    val angleRad = Math.toRadians(itemAngle.toDouble())

                    val x = (layoutRadius * cos(angleRad)).toFloat()
                    val y = (layoutRadius * sin(angleRad)).toFloat()

                    val centerX = constraints.maxWidth / 2
                    val centerY = constraints.maxHeight / 2

                    val posX = (centerX + x - placeable.width / 2).roundToInt()
                    val posY = (centerY + y - placeable.height / 2).roundToInt()

                    placeable.placeWithLayer(
                        x = posX,
                        y = posY,
                        layerBlock = {
                            rotationZ = when (itemRotationMode) {
                                ItemRotationMode.Inward -> itemAngle - 90f
                                ItemRotationMode.Outward -> itemAngle + 90f
                                ItemRotationMode.None -> 0f
                            }
                        }
                    )
                }
            }
        }
    }
}