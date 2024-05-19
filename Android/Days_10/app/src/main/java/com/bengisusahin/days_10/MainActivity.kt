package com.bengisusahin.days_10

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bengisusahin.days_10.adapters.PostAdapter
import com.bengisusahin.days_10.datas.DummyUsers

class MainActivity : AppCompatActivity() {

    lateinit var postList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        postList = findViewById(R.id.recyclerView_postList)
        postList.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

        // sync gson dönüştürme esnasında main thredi kısa süre de olsa kilitler
        // cok büyük b verin yoksa async yapmana gerek yok
        val posts = DummyUsers.getUsers().posts
        //Log.d("posts", posts.toString())
        val adapter = PostAdapter(posts)
        postList.adapter = adapter

    }
}