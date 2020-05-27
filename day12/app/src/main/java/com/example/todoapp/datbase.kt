package com.example.todoapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.w3c.dom.Text
import java.lang.Exception

class datbase(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int )
    : SQLiteOpenHelper(context, "student_info1", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        try {
            var createtable = "CREATE TABLE students(id INTEGER PRIMARY KEY,title TEXT,work TEXT)"
            db?.execSQL(createtable)
            Log.e("message","table has been created")
        }
        catch (e : Exception)
        {
            Log.e("message","Exception $e")
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            var drop = """DROP TABLE students """
            db?.execSQL(drop)
            onCreate(db)
        }
        catch (e : Exception)
        {
            Log.e("message","Exception $e")
        }
    }
    // crud
    // create ,  read , update , delete
    fun adddata(contact: contact)
    {
        try
        {
            var db: SQLiteDatabase = this.writableDatabase
            var value =ContentValues()
            value.put("title",contact.title)
            value.put("work",contact.work)
            db.insert("students",null, value)
            db.close()
            Log.e("message","data inserted")

        }catch (e : Exception)
        {
            Log.e("message","Exception $e")
        }
    }

    fun getalldata(): MutableList<contact>
    {
        var listofdata : MutableList<contact> = ArrayList()

        var db: SQLiteDatabase = this.readableDatabase
        var query = "select * from students "

        var result = db.rawQuery(query,null)

        if (result.moveToFirst())
        {
            do
            {
                var contact = contact()
                contact.id = result.getString(0).toInt()
                contact.title = result.getString(1)
                contact.work = result.getString(2)
                listofdata.add(contact)
            }
            while (result.moveToNext())
        }
        return listofdata
    }

    fun getone(id :Int):contact
    {
        var db: SQLiteDatabase = this.readableDatabase
        var query = "select * from students where id = ${id.toString()}"
        var result = db.rawQuery(query,null,null )

        if(result != null)
            result.moveToFirst()

        var contact = contact()
        if(result !=null) {

            contact.id = result.getString(0).toInt()
            contact.title = result.getString(1)
            contact.work = result.getString(2)
        }
        return contact
    }
}

