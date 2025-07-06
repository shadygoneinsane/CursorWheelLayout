package com.cursorwheellayout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cursorwheel.CursorWheelView
import com.cursorwheel.CursorWheelView.OnMenuSelectedListener
import com.cursorwheel.demo.R
import com.cursorwheel.demo.databinding.ActivitySampleWheelBinding
import com.cursorwheellayout.adapter.SimpleImageAdapter
import com.cursorwheellayout.adapter.SimpleTextAdapter
import com.cursorwheellayout.data.ImageData
import com.cursorwheellayout.data.MenuItemData
import java.util.Random
import android.view.Gravity // Added import

class SampleWheelActivity : AppCompatActivity(), OnMenuSelectedListener {
    private lateinit var viewBinding: ActivitySampleWheelBinding

    private var mRandom = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample_wheel)
        initData()
    }

    private fun initData() {
        val res = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "OFF")
        val menuItemDatas: MutableList<MenuItemData> = ArrayList()
        for (i in res.indices) {
            menuItemDatas.add(MenuItemData(res[i]))
        }
        val simpleTextAdapter =
            SimpleTextAdapter(this, menuItemDatas, Gravity.TOP or Gravity.CENTER_HORIZONTAL)
        viewBinding.testCircleMenuLeft.setAdapter(simpleTextAdapter)
        viewBinding.testCircleMenuLeft.setOnMenuSelectedListener(this)

        val simpleTextAdapter2 =
            SimpleTextAdapter(this, menuItemDatas, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
        viewBinding.testCircleMenuRight.setAdapter(simpleTextAdapter2)
        viewBinding.testCircleMenuRight.setOnMenuSelectedListener(this)
        val imageDatas: MutableList<ImageData> = ArrayList()
        imageDatas.add(ImageData(R.drawable.ic_bank_bc, "0"))
        imageDatas.add(ImageData(R.drawable.ic_bank_china, "1"))
        imageDatas.add(ImageData(R.drawable.ic_bank_guangda, "2"))
        imageDatas.add(ImageData(R.drawable.ic_bank_guangfa, "3"))
        imageDatas.add(ImageData(R.drawable.ic_bank_jianshe, "4"))
        imageDatas.add(ImageData(R.drawable.ic_bank_jiaotong, "5"))
        val simpleImageAdapter = SimpleImageAdapter(this, imageDatas)
        viewBinding.testCircleMenuTop.setAdapter(simpleImageAdapter)
        viewBinding.testCircleMenuTop.setOnMenuSelectedListener(object : OnMenuSelectedListener {
            override fun onItemSelected(parent: CursorWheelView, view: View?, pos: Int) {
                Toast.makeText(
                    this@SampleWheelActivity,
                    "Top Menu selected position:$pos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewBinding.testCircleMenuTop.setOnMenuItemClickListener(object :
            CursorWheelView.OnMenuItemClickListener {
            override fun onItemClick(view: View?, pos: Int) {
                Toast.makeText(
                    this@SampleWheelActivity,
                    "Top Menu click position:$pos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewBinding.mainButtonRandomSelected.setOnClickListener {
            onRandomClick()
        }
    }

    override fun onItemSelected(p: CursorWheelView, view: View?, pos: Int) {}

    private fun onRandomClick() {
        val index = mRandom.nextInt(10)
        viewBinding.testCircleMenuLeft.setSelection(index)
        viewBinding.testCircleMenuRight.setSelection(index, false)
        viewBinding.mainButtonRandomSelected.text = "Random Selected:$index"
    }
}