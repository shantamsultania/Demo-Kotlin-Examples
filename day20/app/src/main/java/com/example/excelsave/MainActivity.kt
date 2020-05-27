package com.example.excelsave
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.opencsv.CSVWriter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileWriter
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        save_btn.setOnClickListener {

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),0)
            }
            else
            {
             try
             {
                 var name = text1.text.toString()
                 var lastname = text2.text.toString()
                 var file = File("/sdcard/newfolder/")
                 file.mkdirs()

                 var csv = "/sdcard/newfolder/excel.csv"
                 var csvwritter = CSVWriter(FileWriter(csv,true))
                 var row= arrayOf(name,lastname)
                 csvwritter.writeNext(row)
                 csvwritter.close()
                 Toast.makeText(this,"done",Toast.LENGTH_SHORT).show()

             }
             catch (e:Exception)
             {
                 Log.e("e","$e")
                 e.printStackTrace()
             }
            }
        }

    }
}
