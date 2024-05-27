package com.bengisusahin.days_11.configs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DB(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        // project.db olması şart değil
        private val DATABASE_NAME = "project.db"
        private val DATABASE_VERSION = 1
    }

    // sürekli calismaz program ilk ayaga kalktiginda
    override fun onCreate(db: SQLiteDatabase?) {
        val contactsSql = "CREATE TABLE \"contacts\" (\n" +
                "\t\"cid\"\tINTEGER,\n" +
                "\t\"name\"\tTEXT,\n" +
                "\t\"surname\"\tTEXT,\n" +
                "\t\"age\"\tINTEGER,\n" +
                "\t\"color\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"cid\" AUTOINCREMENT)\n" +
                ");"
        db?.let {
            it.execSQL(contactsSql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val contactsDropSql = "DROP TABLE IF EXISTS contacts"
        db?.let {
            it.execSQL(contactsDropSql)
            onCreate(it)
        }
    }

}