package com.bengisusahin.odev_10.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengisusahin.odev_10.R
import com.bengisusahin.odev_10.adapter.NoteAdapter
import com.bengisusahin.odev_10.databinding.ActivityMainBinding
import com.bengisusahin.odev_10.models.Note
import com.bengisusahin.odev_10.services.NoteService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteService: NoteService
    private lateinit var noteAdapter: NoteAdapter
    lateinit var allNotes:MutableList<Note>
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        noteService = NoteService(this)
        userId = intent.getIntExtra("userId", -1)

        // Notları al ve RecyclerView'e ekle
        allNotes = noteService.getNotesForUser(userId)
        Log.d("allNotes",allNotes.toString())
        noteAdapter = NoteAdapter(allNotes)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = noteAdapter

        binding.floatingActionAddNoteButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }
}