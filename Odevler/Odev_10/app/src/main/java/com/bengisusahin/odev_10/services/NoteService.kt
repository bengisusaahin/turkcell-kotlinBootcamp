package com.bengisusahin.odev_10.services

import android.content.ContentValues
import android.content.Context
import com.bengisusahin.odev_10.configs.DB
import com.bengisusahin.odev_10.models.Note

class NoteService(context: Context) : DB(context) {

    fun addNote(uid: Int, title: String, content: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_USER_ID_FK, uid)
            put(COLUMN_NOTE_TITLE, title)
            put(COLUMN_NOTE_CONTENT, content)
            //put(COLUMN_NOTE_DATE, date )
        }
        val effectRow = db.insert(TABLE_NOTES, null, contentValues)
        db.close()
        return effectRow
    }

    fun getNotes(): MutableList<Note> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NOTES", null)
        val notes = mutableListOf<Note>()
        while (cursor.moveToNext()) {
            val note = Note(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NOTE_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID_FK)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_TITLE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_CONTENT))
                //cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_DATE))
            )
            notes.add(note)
        }
        cursor.close()
        db.close()
        return notes
    }

    fun getNotesForUser(uid: Int): MutableList<Note> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NOTES WHERE $COLUMN_USER_ID_FK = ?", arrayOf(uid.toString()))
        val notes = mutableListOf<Note>()
        while (cursor.moveToNext()) {
            val note = Note(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NOTE_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID_FK)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_TITLE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_CONTENT))
                //cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_DATE))
            )
            notes.add(note)
        }
        cursor.close()
        db.close()
        return notes
    }
    fun getNoteById(nid: Int): Note? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NOTES WHERE $COLUMN_NOTE_ID = ?", arrayOf(nid.toString()))
        val note = if (cursor.moveToNext()) {
            Note(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NOTE_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID_FK)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_TITLE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_TITLE))
                //cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_DATE))
            )
        } else {
            null
        }
        cursor.close()
        db.close()
        return note
    }

    fun updateNote(note: Note): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOTE_TITLE, note.title)
            put(COLUMN_NOTE_CONTENT, note.content)
        }
        val updateStatus = db.update(TABLE_NOTES, values, "$COLUMN_NOTE_ID = ?", arrayOf(note.nid.toString()))
        db.close()
        return updateStatus
    }

    fun deleteNoteById(nid: Int): Int {
        val db = this.writableDatabase
        val deleteStatus = db.delete(TABLE_NOTES, "$COLUMN_NOTE_ID = ?", arrayOf(nid.toString()))
        db.close()
        return deleteStatus
    }
}