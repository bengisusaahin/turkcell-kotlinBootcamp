package com.bengisusahin.days_9

import Products
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_9.adapters.ProductAdapters
import com.bengisusahin.days_9.configs.ApiClient
import com.bengisusahin.days_9.services.IDummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var iDummyService : IDummyService
    lateinit var listViewProducts : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listViewProducts = findViewById(R.id.listViewProducts)

        iDummyService = ApiClient.getClient().create(IDummyService::class.java)
        iDummyService.getProducts().enqueue(object: Callback<Products> {
            override fun onResponse(p0: Call<Products>, p1: Response<Products>) {
                if (p1.isSuccessful){
                    val arr = p1.body()!!.products
                    //Log.d("arr", arr.toString())
                    val productAdapters = ProductAdapters(this@MainActivity, arr)
                    listViewProducts.adapter = productAdapters
                }
            }

            override fun onFailure(p0: Call<Products>, p1: Throwable) {
                Log.e("getProducts", p1.message!!)
            }

        })
    }


}