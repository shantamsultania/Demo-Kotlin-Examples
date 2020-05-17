package com.example.sqlapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class datbase(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int )
    : SQLiteOpenHelper(context, "demo", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        try {
            var createtable = "CREATE TABLE person (id INTEGER PRIMARY KEY,name TEXT,number TEXT,email TEXT)"
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
            var drop = """DROP TABLE person """
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
          value.put("name",contact.name)
          value.put("number",contact.number)
          value.put("email",contact.email)

          db.insert("person",null, value)
          db.close()
          Log.e("message","data inserted")

      }catch (e : Exception)
      {
          Log.e("message","Exception $e")
      }
    }
    fun getdata(): MutableList<contact>
    {
        var list : MutableList<contact> = ArrayList()
        var db: SQLiteDatabase = this.readableDatabase
        var query = "select * from person"

        var result = db.rawQuery(query,null)

        if (result.moveToFirst())
        {
            do
            {
             var contact = contact()
                contact.id = result.getString(0).toInt()
                contact.name = result.getString(1)
                contact.number = result.getString(2)
                contact.email = result.getString(3)
                list.add(contact)
            }
            while (result.moveToNext())
        }
        return list
    }
}