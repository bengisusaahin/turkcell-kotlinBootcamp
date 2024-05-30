package com.bengisusahin.odev_10.view

import android.os.Bundle
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
            noteService.deleteNoteById(noteId)
            finish()
        }
    }
}