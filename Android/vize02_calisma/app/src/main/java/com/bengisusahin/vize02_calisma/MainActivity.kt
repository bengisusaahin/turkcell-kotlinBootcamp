package com.bengisusahin.vize02_calisma

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.vize02_calisma.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        signUp()

        val popUp = PopupMenu(this, binding.buttonSignUpWith )
        popUp.menuInflater.inflate(R.menu.main, popUp.menu)
        popUp.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.itm_google -> {
                    Log.d("Item-", "Google Click")
                    true
                }
                R.id.itm_instagram -> {
                    Log.d("Item-", "Instagram Click")
                    true
                }
                R.id.itm_facebook -> {
                    Log.d("Item-", "Facebook Click")
                    true
                }
                else -> false
            }
        }
        binding.buttonSignUpWith.setOnClickListener {
            popUp.show()
        }
    }

    fun signUp() {
        binding.buttonSignUp.setOnClickListener {
            val name = binding.txtName.text.toString()
            val username = binding.txtUsername.text.toString()
            val password = binding.textPassword.text.toString()

            if (name == "Bengisu" && username== "bengisu" && password == "123456") {
                val intent = Intent(this, DetailActivity::class.java )
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}