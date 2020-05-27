package com.example.todoapp

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup.view.*
import kotlinx.android.synthetic.main.popup.view.save_btn

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Welcome to TODO application enter your TODO work here", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            popup()
        }
    }

    private fun popup() {

        val datbase = datbase(this,"student_info1", null, 1)
        val dialogBuilder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.popup,null)
       var  title = view.titleedit
        val wordk = view.workedit
        dialogBuilder.setView(view)
        val dialog= dialogBuilder.create()
        dialog.show()
//        val getlist = datbase.getdata()
//        for(contact in getlist)
//        {
//            Log.e("message","data id is  ${contact.id} ")
//            Log.e("message","the title is${contact.title}")
//            Log.e("message","work is  ${contact.work} ")
//        }
        view.save_btn.setOnClickListener {

            val data = contact()
            data.title= title.text.toString()
            data.work = wordk.text.toString()
            datbase.adddata(data)

            // to get all the data
            var getlist = datbase.getalldata()
            for ( i in getlist)
            {
                Log.e("message","data id is ${i.id}")
            }

            // to get one data at a time
            var j = datbase.getone(2)
            Log.e("message","data id is ${j.title}")

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
