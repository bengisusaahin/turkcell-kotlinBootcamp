package com.bengisusahin.odev_10.view

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengisusahin.odev_10.adapter.NoteAdapter
import com.bengisusahin.odev_10.databinding.ActivityAddNoteBinding
import com.bengisusahin.odev_10.models.Note
import com.bengisusahin.odev_10.services.NoteService
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
        userId = intent.getIntExtra("userId", -1)

        showDatePickerDialog()

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val date = binding.dateEditText.text.toString()
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
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        binding.dateEditText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val formattedDate = dateFormat.format(selectedDate.time)
                    binding.dateEditText.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }
}
