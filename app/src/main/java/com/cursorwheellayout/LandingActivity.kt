package com.cursorwheellayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cursorwheel.demo.R

class LandingActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupStatusBar()
        setContentView(R.layout.activity_landing)
        
        setupButtons()
    }
    
    private fun setupStatusBar() {
        // Clear any fullscreen flags to ensure status bar is visible
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
    
    private fun setupButtons() {
        val btnOriginalDemo = findViewById<Button>(R.id.btn_original_demo)
        val btnSampleWheel = findViewById<Button>(R.id.btn_sample_wheel)
        val btnComposeWheel = findViewById<Button>(R.id.btn_compose_wheel)
        val btnAdvancedWheel = findViewById<Button>(R.id.btn_advanced_wheel)
        val btnComposeMain = findViewById<Button>(R.id.btn_compose_main)
        
        btnOriginalDemo.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        
        btnSampleWheel.setOnClickListener {
            startActivity(Intent(this, SampleWheelActivity::class.java))
        }
        
        btnComposeWheel.setOnClickListener {
            startActivity(Intent(this, ComposeWheelActivity::class.java))
        }
        
        btnAdvancedWheel.setOnClickListener {
            startActivity(Intent(this, AdvancedComposeWheelActivity::class.java))
        }
        
        btnComposeMain.setOnClickListener {
            startActivity(Intent(this, ComposeMainActivity::class.java))
        }
    }
}