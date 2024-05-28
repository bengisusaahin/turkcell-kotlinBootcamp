package com.bengisusahin.days_11.services

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.bengisusahin.days_11.configs.DB
import com.bengisusahin.days_11.models.Contact

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

    fun deleteContact(cid: Int) : Int {
        val db = this.writableDatabase
        val deleteStatus = db.delete("contacts", "cid = $cid", null)
        // ? bind value
        //val deleteStatus = db.delete("contacts", "name = ?, surname = ?", arrayOf("ali", "bilmem"))
        db.close()
        return deleteStatus
    }

    fun updateContact(cid: Int, name: String, surname: String, age : Int, color: String) : Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("surname", surname)
        values.put("age", age)
        values.put("color", color)

        val updateStatus = db.update("contacts", values, "cid = $cid", null)
        db.close()
        return updateStatus
    }


    fun getContacts() : List<Contact> {
        val db = this.readableDatabase
        val arrayList = mutableListOf<Contact>()
        // cursor = veritabanındaki database in içindeki veriyi yakalayabilecek olan mekanizma
        //val cursorQ = db.query("contacts", null, null, null, null, null, null)
        val cursor = db.rawQuery("SELECT * FROM contacts", null)
        while (cursor.moveToNext()) {
            val cid = cursor.getInt(cursor.getColumnIndexOrThrow("cid"))
            val name = cursor.getString(1)
            val surname = cursor.getString(2)
            val age = cursor.getInt(3)
            val color = cursor.getString(4)

            //Log.d("sql", "$cid - $name - $surname - $age - $color")

            val c = Contact(cid, name, surname, age, color)
            arrayList.add(c)
        }
        cursor.close()
        db.close()
        return arrayList
    }

    fun searchContacts(q: String) : List<Contact> {
        val db = this.readableDatabase
        val arrayList = mutableListOf<Contact>()
        //val cursorQ = db.query("contacts", null, null, null, null, null, null)
        val cursor = db.rawQuery("SELECT * FROM contacts WHERE name LIKE '%$q%' or surname LIKE '%$q%'", null)
        while (cursor.moveToNext()) {
            val cid = cursor.getInt(cursor.getColumnIndexOrThrow("cid"))
            val name = cursor.getString(1)
            val surname = cursor.getString(2)
            val age = cursor.getInt(3)
            val color = cursor.getString(4)

            //Log.d("sql", "$cid - $name - $surname - $age - $color")

            val c = Contact(cid, name, surname, age, color)
            arrayList.add(c)
        }
        cursor.close()
        db.close()
        return arrayList
    }

    fun singleContact(name: String, surname: String) : Contact? {
        val db = this.readableDatabase
        // sql injection ı engellemek için ? kullanılır ve arrayOf içine yazılır bind value
        val cursor = db.rawQuery("SELECT * FROM contacts WHERE name = ? and surname = ?", arrayOf(name, surname))
        if (cursor.moveToNext()) {
            val cid = cursor.getInt(cursor.getColumnIndexOrThrow("cid"))
            val name = cursor.getString(1)
            val surname = cursor.getString(2)
            val age = cursor.getInt(3)
            val color = cursor.getString(4)

            val c = Contact(cid, name, surname, age, color)
            cursor.close()
            db.close()
            return c
        }
        // if e girmezse
        cursor.close()
        db.close()
        return null
    }
}