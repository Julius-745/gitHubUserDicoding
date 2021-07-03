package com.example.gituser_submission2.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gituser_submission2.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Handler(mainLooper).postDelayed({
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}