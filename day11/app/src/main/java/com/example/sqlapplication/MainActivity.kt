package com.example.sqlapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var datbase = datbase(this,"demo", null, 1)
        var data1 = contact()
        data1.name="shantam"
        data1.email = "shantam1230@gmail.com"
        data1.number = "898989989"
        datbase.adddata(data1)
        var getlist = datbase.getdata()
        for(contact in getlist)
        {
            Log.e("message","data id is  ${contact.id} name is  ${contact.name}")
            Log.e("message","data number is  ${contact.number} email is  ${contact.email}")
        }
    }
}
