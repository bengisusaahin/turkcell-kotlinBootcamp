package com.bengisusahin.days_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_4.models.Customer

class MainActivity : AppCompatActivity() {

    lateinit var btnGoToDetail : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnGoToDetail = findViewById(R.id.btnGoToDetail)

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
            startActivity(i)
        }
    }
}