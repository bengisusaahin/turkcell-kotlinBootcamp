package com.bengisusahin.vize03_calisma.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.vize03_calisma.R
import com.bengisusahin.vize03_calisma.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding
    private var selectedBloodGroup: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFilterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.linearLayout.setOnClickListener {
            showPopup(it)
        }

        binding.buttonBack.setOnClickListener {
            val intent = Intent()
            val firstName = binding.editTextFirstName.text.toString()
            val lastName = binding.editTextLastName.text.toString()
            val age = binding.editTextAge.text.toString()
            val bloodGroup = selectedBloodGroup

            when {
                firstName.isNotEmpty() && lastName.isEmpty() && age.isEmpty() && bloodGroup.isNullOrEmpty() -> {
                    intent.putExtra("key", "firstName")
                    intent.putExtra("value", firstName)
                    setResult(RESULT_OK, intent)
                }
                lastName.isNotEmpty() && firstName.isEmpty() && age.isEmpty() && bloodGroup.isNullOrEmpty() -> {
                    intent.putExtra("key", "lastName")
                    intent.putExtra("value", lastName)
                    setResult(RESULT_OK, intent)
                }
                age.isNotEmpty() && firstName.isEmpty() && lastName.isEmpty() && bloodGroup.isNullOrEmpty() -> {
                    intent.putExtra("key", "age")
                    intent.putExtra("value", age)
                    setResult(RESULT_OK, intent)
                }
                !bloodGroup.isNullOrEmpty() && firstName.isEmpty() && lastName.isEmpty() && age.isEmpty() -> {
                    intent.putExtra("key", "bloodGroup")
                    intent.putExtra("value", bloodGroup)
                    setResult(RESULT_OK, intent)
                }
                firstName.isEmpty() && lastName.isEmpty() && age.isEmpty() -> {
                    //TODO kullanıcıya uyarı mesajı gösterilebilir
                    /*setResult(RESULT_CANCELED)
                    finish()
                    return@setOnClickListener

                     */
                }
                else -> {
                    // If more than one field is entered or no field is entered
                    Toast.makeText(this, "Please fill only one filter.", Toast.LENGTH_LONG).show()
                    // "return@setOnClickListener" just stops the button click event and the onCreate function continues
                    return@setOnClickListener
                }
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.buttonClear.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun showPopup(view: View) {
        val popup = PopupMenu(this, view, Gravity.FILL)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.filter_blood_group, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            selectedBloodGroup = menuItem.title.toString()
            Toast.makeText(this, "Selected: $selectedBloodGroup", Toast.LENGTH_SHORT).show()
            true
        }
        popup.show()
    }
}