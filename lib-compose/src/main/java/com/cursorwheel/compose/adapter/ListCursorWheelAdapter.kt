package com.cursorwheel.compose.adapter

/**
 * Simple implementation of CursorWheelAdapter that wraps a list
 */
class ListCursorWheelAdapter<T>(private val items: List<T>) : CursorWheelAdapter<T> {
    override fun getCount(): Int = items.size
    override fun getItem(position: Int): T = items[position]
}

/**
 * Extension function to create a CursorWheelAdapter from a List
 */
fun <T> List<T>.toCursorWheelAdapter(): CursorWheelAdapter<T> = ListCursorWheelAdapter(this)