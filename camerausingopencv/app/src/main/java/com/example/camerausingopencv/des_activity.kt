package com.example.camerausingopencv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_des_activity.*

class des_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_des_activity)

        Toast.makeText(this,"Please Check Camera , Internet Permission",Toast.LENGTH_SHORT).show()
        start_btn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            Snackbar.make(it, "Welcome to Unknown Canny ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        know_btn.setOnClickListener {
            val intent = Intent(this,webview::class.java)
            startActivity(intent)
            Snackbar.make(it, "Welcome to Unknown Canny... lets checkout more Projects", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
