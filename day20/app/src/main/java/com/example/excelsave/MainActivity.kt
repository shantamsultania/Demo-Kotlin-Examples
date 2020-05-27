package com.example.excelsave
import android.Manifest
import android.annotation.SuppressLint
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
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.InputStreamReader
import java.lang.Exception
import com.example.excelsave.R.raw.testing as testing1

class MainActivity : AppCompatActivity() {


    @SuppressLint("SdCardPath")
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
                 val name = text1.text.toString()
                 val lastname = text2.text.toString()
                 val file = File("/sdcard/newfolder/")
                 file.mkdirs()

                 val csv = "/sdcard/newfolder/excel.csv"
                 val csvwritter = CSVWriter(FileWriter(csv,true))
                 val row= arrayOf(name,lastname)
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

        var inputStream = resources.openRawResource(R.raw.testing)
        var br = BufferedReader(InputStreamReader(inputStream))
        var csvLine = br.readLine()
        var data:List<String>
        show_data_btn.setOnClickListener {

            try{

                while ((csvLine) != null)
                {

                    try {
                        data = csvLine.split(",")
                        csvLine = br.readLine()
                        Log.e("excel ", data[0])

                    }catch (e2:Exception)
                    {
                        Log.e("excel","$e2")
                    }

                }
            }catch (e1:Exception)
            {
                Log.e("excel","$e1")
            }



        }

    }
}
