package com.bengisusahin.odev_10.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengisusahin.odev_10.adapter.NoteAdapter
import com.bengisusahin.odev_10.databinding.ActivityAddNoteBinding
import com.bengisusahin.odev_10.models.Note
import com.bengisusahin.odev_10.services.NoteService
import com.bengisusahin.odev_10.utils.DateUtils

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var noteService: NoteService
    lateinit var allNotes:MutableList<Note>
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteService = NoteService(this)
        // get the userId from the intent that started this activity
        userId = intent.getIntExtra("userId", -1)

        DateUtils.showDatePickerDialog(this, binding.dateEditText)

        // save the note to the database when the save button is clicked
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val date = binding.dateEditText.text.toString()

            if (title.isEmpty() || content.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                // stops the execution of the code if any of the fields are empty
                return@setOnClickListener
            }else {
                val noteId = noteService.addNote(userId, title, content, date)
                // if noteId is greater than -1, it means note is added successfully, addNote method returns the id of the note
                if (noteId > -1) {
                    binding.titleEditText.text.clear()
                    binding.contentEditText.text.clear()

                    Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show()

                    // after adding the note, update the list
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }
    }
}