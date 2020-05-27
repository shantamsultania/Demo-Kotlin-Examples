package com.example.webinar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ViewFlipper

class MainActivity : AppCompatActivity() {

    lateinit var viewflipper: ViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var array = arrayOf(R.drawable.aada,R.drawable.download,R.drawable.dadda,R.drawable.kotlin)

        viewflipper = findViewById(R.id.slideshow)

        for ( i in array)
        {
            slideshow(i)
        }

    }
    fun slideshow(Image: Int)
    {
        val Img = ImageView(this)
        Img.setBackgroundResource(Image)
        viewflipper.addView(Img)
        viewflipper.flipInterval = 4000
        viewflipper.isAutoStart = true

    }
}
