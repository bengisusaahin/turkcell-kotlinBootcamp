package com.bengisusahin.days_3

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtName.setText("New Data")

        binding.btnSend.setOnClickListener {
            //Log.d("olay", "btn click")
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("dataKey", binding.txtName.text.toString())
            startActivity(intent)
            //finish() // Bu nesneyi öldür

        }

        //binding.btnSend.setOnClickListener(clickBtn)

        //this -> override onClick
        //binding.main.setOnClickListener(this)
    }

//    val clickBtn = View.OnClickListener {  }

//    override fun onClick(v: View?) {
//        TODO("Not yet implemented")
//    }
}