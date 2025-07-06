package com.cursorwheel.compose.internal

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.sin

/**
 * Draw cursor indicator at the selected angle position
 */
internal fun DrawScope.drawCursor(
    centerX: Float,
    centerY: Float,
    radius: Float,
    selectedAngle: Float,
    cursorColor: Color,
    cursorHeight: Float
) {
    val angleRad = Math.toRadians(selectedAngle.toDouble())
    val cursorStartRadius = radius - cursorHeight
    val cursorEndRadius = radius

    val startX = centerX + (cursorStartRadius * cos(angleRad)).toFloat()
    val startY = centerY + (cursorStartRadius * sin(angleRad)).toFloat()
    val endX = centerX + (cursorEndRadius * cos(angleRad)).toFloat()
    val endY = centerY + (cursorEndRadius * sin(angleRad)).toFloat()

    drawLine(
        color = cursorColor,
        start = Offset(startX, startY),
        end = Offset(endX, endY),
        strokeWidth = 4f
    )
}

/**
 * Draw guide lines from center to each item position
 */
internal fun DrawScope.drawGuideLines(
    centerX: Float,
    centerY: Float,
    radius: Float,
    itemCount: Int,
    startAngle: Float,
    guideLineColor: Color,
    guideLineWidth: Float
) {
    if (itemCount == 0) return

    val angleStep = 360f / itemCount

    for (i in 0 until itemCount) {
        val itemAngle = startAngle + i * angleStep
        val angleRad = Math.toRadians(itemAngle.toDouble())

        val endX = centerX + (radius * cos(angleRad)).toFloat()
        val endY = centerY + (radius * sin(angleRad)).toFloat()

        drawLine(
            color = guideLineColor,
            start = Offset(centerX, centerY),
            end = Offset(endX, endY),
            strokeWidth = guideLineWidth
        )
    }
}