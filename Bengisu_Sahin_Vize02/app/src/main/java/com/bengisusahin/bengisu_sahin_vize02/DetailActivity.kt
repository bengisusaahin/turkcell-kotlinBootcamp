package com.bengisusahin.bengisu_sahin_vize02

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.bengisu_sahin_vize02.databinding.ActivityDetailBinding
import com.bengisusahin.bengisu_sahin_vize02.models.Plant

class DetailActivity : AppCompatActivity() {

    private lateinit var bindingDetail : ActivityDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        val viewDetail = bindingDetail.root

        enableEdgeToEdge()

        setContentView(viewDetail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // MAinActivityde Xml serviceden çekilden bitkinin detaylarını bu aktivityde göstermek icin
        // getStringExtra methoddunu kullandık
        val detail = intent.getSerializableExtra("detail") as Plant

        //Log.d("detail", textDetail!!)
        bindingDetail.textViewDetail.text ="${detail.COMMON} \n${detail.BOTANICAL} \n${detail.ZONE}" +
                "\n${detail.LIGHT}\n${detail.PRICE}\n${detail.AVAILABILITY}"


        // geri butonuna basıldığında main activitye dönülmesi icin startActivity(intent)
        // finish sayesinde de butona basmadan geri buraya gelemiyoruz
        bindingDetail.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}