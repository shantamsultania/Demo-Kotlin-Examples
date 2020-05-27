package com.example.webinar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splash : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // clssname obje = new class()

        //var obj = classname()
        handler = Handler()

        // alt + enter
        handler.postDelayed(
            {
                // intent
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            },5000
        )


    }
}
