package com.bengisusahin.days_12

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.bengisusahin.days_12.configs.AppDatabase
import com.bengisusahin.days_12.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this, AppDatabase::class.java, "pro1"
        ).allowMainThreadQueries().build()

        val pro = Product(null, "Tv-1", "Tv Detail-1", 215000.0f)
        val dao = db.productDao()

        // allowMainThreadQueries yazarsan buna gerek yok
        GlobalScope.launch(Dispatchers.IO) {
            try {
                Thread.sleep(3000)
            }catch (e: Exception){
            }
        }

        val insertStatus = dao.insert(pro)
        Log.d("insertStatus", "$insertStatus")

        // global scope içindeki insert işlemi uzun sürerse buraya eklenen veri gelmemiş olur
        val arr = dao.getAll()
        for (item in arr) {
            Log.d("item", item.toString())
        }

    }
}