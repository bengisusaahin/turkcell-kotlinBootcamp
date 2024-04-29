package com.bengisusahin.days_4

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_4.models.Customer

class DetailActivity : AppCompatActivity() {

    companion object{
        var customer: Customer? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // get extras
        // intent-> appCompatActivityden geliyo
        // null pointer exception almamak için default value, kotlinin null safety sloganından gelme
        val age = intent.getIntExtra("age",0)
        // ilkel(primitive) türlerin default değerleri yok ama String(referans tip) in var o yüzden burda yazmaya gerek kalmıyo
        val name = intent.getStringExtra("name")
        Log.d("age",age.toString())
        name?.let {
            Log.d("name",it)
            //Log.d("name",name)
        }

        // String ifade bekleniyor ama String? geliyo diye kesin gelcek diyoruz(!!ile)
        // Log.d("name",name!!)

        customer?.let {
            Log.d("Customer", it.toString())
        }
    }
}