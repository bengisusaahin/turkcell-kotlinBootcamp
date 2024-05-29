package com.bengisusahin.odev_10.configs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//Context parameter is used to access specific resources and services of the application
open class DB(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    // companion object is used to define Singleton object within a class
    // Singleton ensure that only one instance(object)of the class is created
    // This object can be accessed directly without creating object of class
    companion object {
        private const val DATABASE_NAME = "user.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "users"
        private const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
    }

    // called when the database is created for the first time
    override fun onCreate(db: SQLiteDatabase?) {
        // primary key is a concept of SQL that uniquely identifies each item in a db table
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)
    }

    // called when the database needs to be upgraded when the db version number is increased
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

}