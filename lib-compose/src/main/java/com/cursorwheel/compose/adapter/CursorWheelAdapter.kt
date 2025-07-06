package com.cursorwheel.compose.adapter

/**
 * Interface for providing data to CursorWheelLayout in a more flexible way.
 * This allows for more dynamic content creation similar to the original CycleWheelAdapter.
 */
interface CursorWheelAdapter<T> {
    /**
     * Return the number of items in the dataset
     */
    fun getCount(): Int

    /**
     * Get the data item associated with the specified position in the data set.
     */
    fun getItem(position: Int): T

    /**
     * Get the type of View that will be created for the specified item.
     * This can be used for view recycling optimization in future versions.
     */
    fun getItemViewType(position: Int): Int = 0

    /**
     * Returns the number of types of Views that will be created.
     */
    fun getViewTypeCount(): Int = 1
}