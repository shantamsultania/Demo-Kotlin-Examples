package com.example.camerausingopencv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class webview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)


        Timer().schedule(3000){
            val intent= Intent(applicationContext,webview2::class.java)
            startActivity(intent)
            finish()
        }
    }
}
