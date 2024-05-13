package com.bengisusahin.days_8

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_8.models.User
import com.google.gson.Gson

class WelcomeActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("customer", MODE_PRIVATE)

        //val token = sharedPreferences.getString("token", "")
        val token = sharedPreferences.getString("token", null)
        val stUser = sharedPreferences.getString("user", null)
        stUser?.let {
            val gson = Gson()
            // jsondan kotlin nesnesine
            val user = gson.fromJson(it, User::class.java)
            Log.d("user", user.toString())

        }

        token?.let {
            if (!it.equals("")){
                // Daha önceden token var!
            }else{
                // bu kişi ilk defa giriş yapıor
            }
        }
    }
}