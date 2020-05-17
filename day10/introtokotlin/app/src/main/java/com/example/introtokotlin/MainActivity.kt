package com.example.introtokotlin

import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewFlipper
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val TAG = "Main"
    lateinit var pref:SharedPreferences
    var key = " name "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(key,Context.MODE_PRIVATE)
        var editor = pref.edit()
        save_btn.setOnClickListener {
            Log.e(TAG, "Saveing .....")
            try{
                var value = editText.text.toString()
                editor.putString(key,value)
                editor.apply()
                Log.e(TAG, "Saved.....")
            }
            catch (e: Exception)
            {
                Log.e(TAG, "exception is $e")
            }
        }

            textView.setText(pref.getString(key,""))
    }
}