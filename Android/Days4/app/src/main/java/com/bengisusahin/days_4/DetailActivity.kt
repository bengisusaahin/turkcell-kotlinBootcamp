package com.bengisusahin.days_4

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_4.models.Customer
import com.bengisusahin.days_4.models.User

class DetailActivity : AppCompatActivity() {

    companion object{
        var customer: Customer? = null
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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

        // Serializable arka tarafta bir maaliyettir aynı zamanda
        val userSerializable = intent.getSerializableExtra("user", User::class.java)
        userSerializable?.let {
            Log.d("user", userSerializable.toString())
        }
        if (userSerializable == null){
            finish()
            System.exit(0)
        }
    }
}