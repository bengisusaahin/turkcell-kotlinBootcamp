package com.bengisusahin.odev_10.services

import android.content.ContentValues
import android.content.Context
import com.bengisusahin.odev_10.configs.DB

class UserService(context: Context) : DB(context) {
    // add user to the database
    fun addUser(username: String, password: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val effectRow = db.insert(TABLE_USERS, null, values)
        db.close()
        return effectRow
    }

    // check if the user exists in the database
    fun getUser(username: String, password: String): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?", arrayOf(username, password))
        val userId = if (cursor.moveToFirst()) {
            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID))
        } else {
            -1
        }
        cursor.close()
        db.close()
        return userId
    }
}