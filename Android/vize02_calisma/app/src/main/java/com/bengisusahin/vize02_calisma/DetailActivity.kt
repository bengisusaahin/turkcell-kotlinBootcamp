package com.bengisusahin.vize02_calisma

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.vize02_calisma.databinding.ActivityDetailBinding
import com.bengisusahin.vize02_calisma.services.XmlService
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = intent.getStringExtra("username")
        Log.d("username", username!!)
        binding.textViewWelcome.text = "Welcome $username"


        binding.buttonGetData.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Get Data")
            builder.setMessage("Are you sure!")

            builder.setPositiveButton("Yes") { dialog, wich ->
                Thread {
                    val currencyList = XmlService().xmlLoad()
                    runOnUiThread {
                        if (currencyList.isNotEmpty()) {
                            // binding.textViewXml.text = currencyList.toString()
                            binding.textViewXml.text = "Currency Name: ${currencyList[0].CurrencyName}\nForex Buying: ${currencyList[0].ForexBuying}"
                            // println(currencyList)
                        } else {
                            Snackbar.make(binding.root, "No data available", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }.start()
            }

            builder.setNegativeButton("No") { dialog, wich ->
                Snackbar.make(this, it, "Data can't be shown", Snackbar.LENGTH_LONG).show()
            }

            builder.setNeutralButton("Cancel") { dialog, wich ->
                Log.d("builder", "Cancel Click")
            }

            builder.setCancelable(false)
            val alert = builder.create()
            alert.show()
        }

    }





}