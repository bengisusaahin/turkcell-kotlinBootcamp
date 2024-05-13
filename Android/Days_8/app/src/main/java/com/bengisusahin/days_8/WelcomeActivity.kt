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

    //lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //sharedPreferences = getSharedPreferences("customer", MODE_PRIVATE)
        SharedPrefHelper.init(this)

        //val token = sharedPreferences.getString("token", "")
        //val token = sharedPreferences.getString("token", null)
        //val stUser = sharedPreferences.getString("user", null)
        val stUserHelper = SharedPrefHelper.getUser()
        stUserHelper?.let {
            //val gson = Gson()
            // jsondan kotlin nesnesine
            //val user = gson.fromJson(it, User::class.java)
            Log.d("user", stUserHelper.toString())
            // bunda direkt string olarak göstermiş oldun üstte user nesnesi olarak
            //Log.d("user", stUser)

        }

//        token?.let {
//            if (!it.equals("")){
//                // Daha önceden token var!
//                Log.d("token", token.toString())
//            }else{
//                // bu kişi ilk defa giriş yapıor
//            }
//        }
    }
}