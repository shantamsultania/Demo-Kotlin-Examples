package com.example.camerausingopencv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class start : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        handler = Handler()

        handler.postDelayed(
            {
                val intent = Intent(this,des_activity::class.java)
                startActivity(intent)
                finish()
            },3000
        )

    }
}
