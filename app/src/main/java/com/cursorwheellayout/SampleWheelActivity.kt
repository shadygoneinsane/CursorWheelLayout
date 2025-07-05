package com.cursorwheellayout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursorwheel.CursorWheelLayout
import com.cursorwheel.CursorWheelLayout.OnMenuSelectedListener
import com.cursorwheel.demo.R
import com.cursorwheellayout.adapter.SimpleTextAdapter
import com.cursorwheellayout.data.MenuItemData

class SampleWheelActivity : AppCompatActivity(), OnMenuSelectedListener {
    
    private lateinit var circularHourMenu: CursorWheelLayout
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupStatusBar()
        setContentView(R.layout.sample_wheel_layout)
        
        initViews()
        setupWheelData()
    }
    
    private fun setupStatusBar() {
        // Clear any fullscreen flags to ensure status bar is visible
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
    
    private fun initViews() {
        circularHourMenu = findViewById(R.id.circular_hour_menu)
    }
    
    private fun setupWheelData() {
        val hours = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23")
        val menuItemData: MutableList<MenuItemData> = ArrayList()
        
        for (hour in hours) {
            menuItemData.add(MenuItemData(hour))
        }
        
        val adapter = SimpleTextAdapter(this, menuItemData, android.view.Gravity.CENTER)
        circularHourMenu.setAdapter(adapter)
        circularHourMenu.setOnMenuSelectedListener(this)
        
        circularHourMenu.setOnMenuItemClickListener(object : CursorWheelLayout.OnMenuItemClickListener {
            override fun onItemClick(view: View?, pos: Int) {
                val selectedHour = hours[pos]
                Toast.makeText(
                    this@SampleWheelActivity,
                    "Selected hour: $selectedHour",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    
    override fun onItemSelected(parent: CursorWheelLayout, view: View?, pos: Int) {
        // Handle item selection if needed
    }
}