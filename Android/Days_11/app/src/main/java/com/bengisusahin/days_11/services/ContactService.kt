package com.bengisusahin.days_11.services

import android.content.ContentValues
import android.content.Context
import com.bengisusahin.days_11.configs.DB

class ContactService(context: Context) : DB(context) {

    /*
    The effectRow variable in your code is of type Long because the insert() method in
    SQLiteDatabase returns a long value representing the row ID of the newly inserted row,
    or -1 if the insertion failed.
     */
    fun addContact(name: String, surname: String, age: Int, color: String) : Long {
        val db = this.writableDatabase
        // hangi sütuna hangi değeri ekleyeceğimizi belirtiyoruz hashmap gibi davranır
        val values = ContentValues()
        values.put("name", name)
        values.put("surname", surname)
        values.put("age", age)
        values.put("color", color)

        val effectRow = db.insert("contacts", null, values)
        db.close()
        return effectRow
    }
}