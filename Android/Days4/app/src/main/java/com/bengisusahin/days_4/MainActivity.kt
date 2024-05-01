package com.bengisusahin.days_4

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_4.models.Customer
import com.bengisusahin.days_4.models.User
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var btnGoToDetail : Button
    lateinit var txtData : EditText
    lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnGoToDetail = findViewById(R.id.btnGoToDetail)
        txtData = findViewById(R.id.txtData)
        btnSend = findViewById(R.id.btnSend)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // this hangi activity nin terk edileceği ama öldürmüyor back stack e atıyor
        // ::class.java derlenmiş class
        btnGoToDetail.setOnClickListener { 
            val i = Intent(this, DetailActivity::class.java)
            // karmaşık verilerin gönderilmesi bu yöntemle kolay değil primitive typler genelde
            i.putExtra("age", 30)
            i.putExtra("name", "Serkan")
            val customer = Customer("Bengisu","Şahin","24")
            DetailActivity.customer = customer

            val user = User(100, "ali01", "12345")
            i.putExtra("user", user)

            startActivity(i)
        }

        btnSend.setOnClickListener {
            val data = txtData.text.toString().trim()
            if (data.equals("")){
                //txtData.setBackgroundColor(Color.RED)
                //Toast.makeText(this, "Data empty!", Toast.LENGTH_SHORT).show()
                Snackbar.make(this, it, "Data empty!", Snackbar.LENGTH_SHORT).show()
            }else{
                Log.d("data", data)
            }
        }
    }
}