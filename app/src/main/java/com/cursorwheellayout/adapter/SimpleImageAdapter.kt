package com.cursorwheellayout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.cursorwheel.CursorWheelLayout.CycleWheelAdapter
import com.cursorwheel.demo.R
import com.cursorwheellayout.data.ImageData

/**
 * Created by chensuilun on 16/4/24.
 */
class SimpleImageAdapter(
    mContext: Context, private val mMenuItemDatas: List<ImageData>
) : CycleWheelAdapter() {

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    override fun getCount(): Int {
        return mMenuItemDatas.size
    }

    override fun getView(parent: View?, position: Int): View {
        val (mImageRes) = getItem(position)
        val root = mLayoutInflater.inflate(R.layout.wheel_image_item, null, false)
        val imgView = root.findViewById<ImageView>(R.id.wheel_menu_item_iv)
        imgView.setImageResource(mImageRes)
        return root
    }

    override fun getItem(position: Int): ImageData {
        return mMenuItemDatas[position]
    }
}