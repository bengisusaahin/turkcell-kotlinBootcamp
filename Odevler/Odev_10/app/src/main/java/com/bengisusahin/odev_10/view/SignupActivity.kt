package com.bengisusahin.odev_10.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.odev_10.R
import com.bengisusahin.odev_10.configs.DB
import com.bengisusahin.odev_10.databinding.ActivitySignupBinding
import com.bengisusahin.odev_10.services.UserService

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var userService: UserService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userService = UserService(this)

        binding.signUpButton.setOnClickListener {
            val username = binding.textUsername.text.toString()
            val password = binding.textPassword.text.toString()
            signUp(username, password)
        }

        binding.textviewLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUp(username: String, password: String) {
        val effectRow = userService.addUser(username, password)
        if (effectRow != -1L) {
            // user added successfully
            Toast.makeText(this, "User signed up successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // user could not be added
            Toast.makeText(this, "User could not be signed up", Toast.LENGTH_SHORT).show()
        }
    }
}