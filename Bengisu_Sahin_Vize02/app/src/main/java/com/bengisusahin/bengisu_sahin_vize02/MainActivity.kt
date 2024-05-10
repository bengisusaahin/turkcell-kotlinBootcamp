package com.bengisusahin.bengisu_sahin_vize02

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.bengisu_sahin_vize02.databinding.ActivityMainBinding
import com.bengisusahin.bengisu_sahin_vize02.models.Plant
import com.bengisusahin.bengisu_sahin_vize02.services.XmlService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var plantList : List<Plant>
    private lateinit var filteredPlant: List<Plant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()

        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bitki listesini almak için thread'i başlatıyoruz
        Thread{
            plantList = XmlService().xmlLoad()
        }.start()

        search()
        detail()

    }

    @SuppressLint("SetTextI18n")
    fun search() {
        // Kod Uı ile etkileşim ierdiği ve UI performansını etkileyecek uzun işlemler ierdiği için runOnUiThread
            binding.buttonArama.setOnClickListener {
                runOnUiThread {
                    val searchText = binding.searchView.query.toString()
                    filteredPlant = plantList.filter { plant ->
                        plant.COMMON.contains(searchText, ignoreCase = true)
                    }
                    // count methoduyla aranan bitkiyle eşleşen bitki sayısını döndürdük
                    binding.textViewBulunan.text = "Bulunan : " + filteredPlant.count().toString()
                }
            }
    }

    private fun detail(){
        binding.buttonDetay.setOnClickListener {
            if (filteredPlant.isNotEmpty()) {
                val intent = Intent(this, DetailActivity::class.java )
                intent.putExtra("detail", filteredPlant[0])
                startActivity(intent)
            } else {
                Toast.makeText(this, "Bir plant bulunamadı.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

/*{
                fun search() {
        // Kod Uı ile etkileşim ierdiği ve UI performansını etkileyecek uzun işlemler ierdiği için runOnUiThread
        runOnUiThread {
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                @SuppressLint("SetTextI18n")
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        filteredPlant = plantList.filter { plant ->
                            plant.COMMON.contains(query, ignoreCase = true)
                        }
                        // count methoduyla aranan bitkiyle eşleşen bitki sayısını döndürdük
                        binding.textViewBulunan.text = "Bulunan : " + filteredPlant.count().toString()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Kullanıcı metin değişikliği yaptığında arama yapılmasını istemediğim için kullanmadım.
                    return true
                }
            })
        }
    }



 */
