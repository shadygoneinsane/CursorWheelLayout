package com.cursorwheel.compose

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
import androidx.compose.runtime.rememberCoroutineScope
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

@Composable
fun <T> CursorWheelLayout(
    items: List<T>,
    modifier: Modifier = Modifier,
    state: CursorWheelState = rememberCursorWheelState(),
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

    val itemCount = items.size
    val angleStep = if (itemCount > 0) WheelConstants.FULL_CIRCLE_DEGREES / itemCount else 0f
    val wheelSizePx = with(density) { wheelSize.toPx() }
    val itemSizePx = with(density) { itemSize.toPx() }
    val paddingPx = wheelSizePx * paddingRatio
    val layoutRadius = (wheelSizePx - itemSizePx) / 2f - paddingPx
    val wheelRadius = wheelSizePx / 2f

    LaunchedEffect(state.angle.value, items.size) {
        if (items.isNotEmpty()) {
            var minAngleDiff = Float.MAX_VALUE
            var newSelectedIndex = 0

            for (i in 0 until itemCount) {
                val itemAngle = state.angle.value + i * angleStep
                val normalizedItemAngle = ((itemAngle % WheelConstants.FULL_CIRCLE_DEGREES) + WheelConstants.FULL_CIRCLE_DEGREES) % WheelConstants.FULL_CIRCLE_DEGREES
                val normalizedSelectedAngle = ((selectedAngle % WheelConstants.FULL_CIRCLE_DEGREES) + WheelConstants.FULL_CIRCLE_DEGREES) % WheelConstants.FULL_CIRCLE_DEGREES

                var angleDiff = abs(normalizedSelectedAngle - normalizedItemAngle)
                if (angleDiff > WheelConstants.HALF_CIRCLE_DEGREES) angleDiff = WheelConstants.FULL_CIRCLE_DEGREES - angleDiff

                if (angleDiff < minAngleDiff) {
                    minAngleDiff = angleDiff
                    newSelectedIndex = i
                }
            }

            if (newSelectedIndex != state.selectedIndex) {
                state.selectedIndex = newSelectedIndex
                onItemSelected(state.selectedIndex, items[state.selectedIndex])
            }
        }
    }

    Box(
        modifier = modifier.size(wheelSize),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = wheelBackgroundColor,
                radius = wheelSizePx / 2f,
                center = center
            )

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

            if (guideLineWidth > 0.dp && guideLineColor != Color.Transparent) {
                drawGuideLines(
                    centerX = center.x,
                    centerY = center.y,
                    radius = wheelSizePx / 2f,
                    itemCount = itemCount,
                    startAngle = state.angle.value,
                    guideLineColor = guideLineColor,
                    guideLineWidth = with(density) { guideLineWidth.toPx() }
                )
            }
        }

        Layout(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(items.size) {
                    awaitEachGesture {
                        val downEvent = awaitFirstDown()
                        val downPosition = downEvent.position
                        val centerX = size.width / 2f
                        val centerY = size.height / 2f

                        val distance = hypot(
                            downPosition.x - centerX,
                            downPosition.y - centerY
                        )

                        if (distance > wheelRadius || distance < WheelConstants.MIN_CENTER_DISTANCE) return@awaitEachGesture

                        var downTime = System.currentTimeMillis().toFloat()
                        var totalRotation = 0f

                        var lastAngle = calculateAngle(
                            downPosition.x - centerX,
                            downPosition.y - centerY
                        )

                        var isClick = true
                        var clickedItemIndex = -1

                        for (i in 0 until itemCount) {
                            val itemAngle = state.angle.value + i * angleStep
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

                        do {
                            val event = awaitPointerEvent()
                            val change = event.changes.first()

                            if (change.pressed) {
                                val currentPosition = change.position
                                val currentAngle = calculateAngle(
                                    currentPosition.x - centerX,
                                    currentPosition.y - centerY
                                )

                                var deltaAngle = currentAngle - lastAngle
                                if (deltaAngle > WheelConstants.HALF_CIRCLE_DEGREES) deltaAngle -= WheelConstants.FULL_CIRCLE_DEGREES
                                if (deltaAngle < -WheelConstants.HALF_CIRCLE_DEGREES) deltaAngle += WheelConstants.FULL_CIRCLE_DEGREES

                                if (abs(deltaAngle) > WheelConstants.CLICK_THRESHOLD_DEGREES) {
                                    isClick = false
                                }

                                totalRotation += deltaAngle

                                scope.launch {
                                    state.angle.snapTo(state.angle.value + deltaAngle)
                                }

                                lastAngle = currentAngle
                                change.consume()
                            }
                        } while (event.changes.any { it.pressed })

                        if (isClick && clickedItemIndex >= 0 && abs(totalRotation) < WheelConstants.ROTATION_THRESHOLD_DEGREES) {
                            onItemClick(clickedItemIndex, items[clickedItemIndex])
                            scope.launch {
                                val targetAngle = calculateShortestPath(
                                    currentAngle = state.angle.value,
                                    targetItemIndex = clickedItemIndex,
                                    angleStep = angleStep,
                                    itemCount = itemCount,
                                    selectedAngle = selectedAngle
                                )
                                state.angle.animateTo(
                                    targetValue = targetAngle,
                                    animationSpec = tween(durationMillis = WheelConstants.SNAP_ANIMATION_DURATION_MS)
                                )
                            }
                        } else {
                            val dragDuration = System.currentTimeMillis() - downTime
                            val velocity = if (dragDuration > 0) {
                                totalRotation / dragDuration * 1000f
                            } else 0f

                            scope.launch {
                                if (abs(velocity) > flingThreshold) {
                                    val decaySpec = exponentialDecay<Float>(
                                        frictionMultiplier = WheelConstants.FRICTION_MULTIPLIER,
                                        absVelocityThreshold = WheelConstants.VELOCITY_THRESHOLD
                                    )

                                    state.angle.animateDecay(
                                        initialVelocity = velocity,
                                        animationSpec = decaySpec
                                    )
                                }

                                snapToNearestItem(state.angle, state.angle.value, angleStep, itemCount, selectedAngle)
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
                        itemContent(index, item, index == state.selectedIndex)
                    }
                }
            }
        ) { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }

            layout(constraints.maxWidth, constraints.maxHeight) {
                placeables.forEachIndexed { index, placeable ->
                    val itemAngle = state.angle.value + index * angleStep
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