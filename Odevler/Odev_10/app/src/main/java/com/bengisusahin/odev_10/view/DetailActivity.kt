package com.bengisusahin.odev_10.view

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.odev_10.R
import com.bengisusahin.odev_10.databinding.ActivityDetailBinding
import com.bengisusahin.odev_10.services.NoteService

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var noteService: NoteService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        noteService = NoteService(this)

        val noteId = intent.getIntExtra("noteId", -1)
        val note = noteService.getNoteById(noteId)

        note?.let {
            binding.apply {
                editTextTitle.setText(it.title)
                editTextContent.setText(it.content)
            }
        }

        binding.deleteButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Yes") { _, _ ->
                    noteService.deleteNoteById(noteId)
                    Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("No",){ _, _ ->
                    finish()
                }
                .setNeutralButton("Cancel", ){dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        binding.updateButton.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val content = binding.editTextContent.text.toString()
            noteService.updateNoteById(noteId, title, content)
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}