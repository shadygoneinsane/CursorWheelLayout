package com.cursorwheel.compose.adapter

/**
 * Mutable implementation of CursorWheelAdapter that allows dynamic updates
 */
class MutableCursorWheelAdapter<T>(private val items: MutableList<T>) : CursorWheelAdapter<T> {
    override fun getCount(): Int = items.size
    override fun getItem(position: Int): T = items[position]

    fun addItem(item: T) {
        items.add(item)
    }

    fun addItem(index: Int, item: T) {
        items.add(index, item)
    }

    fun removeItem(index: Int): T {
        return items.removeAt(index)
    }

    fun updateItem(index: Int, item: T) {
        items[index] = item
    }

    fun clear() {
        items.clear()
    }
}