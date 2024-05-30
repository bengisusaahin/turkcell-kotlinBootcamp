package com.bengisusahin.odev_10.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.odev_10.R
import com.bengisusahin.odev_10.databinding.ActivityLoginBinding
import com.bengisusahin.odev_10.services.UserService

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userService: UserService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userService = UserService(this)

        binding.loginButton.setOnClickListener {
            val username = binding.textUsername.text.toString()
            val password = binding.textPassword.text.toString()
            login(username, password)
        }
        binding.textviewSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            // because the user can go back to the login activity if they press the back button, we don't need to finish the activity
        }
    }

    private fun login(username: String, password: String) {
        val userId = userService.getUser(username, password)
        if (userId != -1) {
            // user exists
            // go to the main activity
            Toast.makeText(this, "User logged in successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("userId", userId)
            }
            startActivity(intent)
            finish()
        } else {
            // user does not exist
            // show a toast message
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}