package com.bengisusahin.vize03_calisma.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    private val filterKey = "key"
    private val filterValue = "value"

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
            handleButtonClick()
        }

        binding.buttonClear.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
        binding.editTextFirstName.addTextChangedListener(filterTextWatcher)
        binding.editTextLastName.addTextChangedListener(filterTextWatcher)
        binding.editTextAge.addTextChangedListener(filterTextWatcher)

    }

    private val filterTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            handleInputChange()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private fun handleInputChange() {
        val firstNameFilled = binding.editTextFirstName.text.isNotEmpty()
        val lastNameFilled = binding.editTextLastName.text.isNotEmpty()
        val ageFilled = binding.editTextAge.text.isNotEmpty()
        val bloodGroupSelected = !selectedBloodGroup.isNullOrEmpty()

        binding.editTextFirstName.isEnabled = !lastNameFilled && !ageFilled && !bloodGroupSelected
        binding.editTextLastName.isEnabled = !firstNameFilled && !ageFilled && !bloodGroupSelected
        binding.editTextAge.isEnabled = !firstNameFilled && !lastNameFilled && !bloodGroupSelected
        binding.linearLayout.isEnabled = !firstNameFilled && !lastNameFilled && !ageFilled
    }
    private fun handleButtonClick() {
        val firstName = formatName(binding.editTextFirstName.text.toString())
        val lastName = formatName(binding.editTextLastName.text.toString())
        val age = binding.editTextAge.text.toString()
        val bloodGroup = selectedBloodGroup

        val intent = Intent().apply {
            when {
                firstName.isNotEmpty() -> setFilter(this, "firstName", firstName)
                lastName.isNotEmpty() -> setFilter(this, "lastName", lastName)
                age.isNotEmpty() -> setFilter(this, "age", age)
                !bloodGroup.isNullOrEmpty() -> setFilter(this, "bloodGroup", bloodGroup)
                else -> {
                    Toast.makeText(this@FilterActivity, "Please fill only one filter.",
                        Toast.LENGTH_LONG).show()
                    setResult(RESULT_CANCELED)
                    finish()
                    return
                }
            }
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun setFilter(intent: Intent, key: String, value: String) {
        intent.putExtra(filterKey, key)
        intent.putExtra(filterValue, value)
    }
    
    private fun showPopup(view: View) {
        val popup = PopupMenu(this, view, Gravity.FILL)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.filter_blood_group, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            selectedBloodGroup = menuItem.title.toString()
            handleInputChange()
            Toast.makeText(this, "Selected: $selectedBloodGroup", Toast.LENGTH_SHORT).show()
            true
        }
        popup.show()
    }

    private fun formatName(name: String): String {
        return if (name.isNotEmpty()) {
            name.lowercase().replaceFirstChar { it.uppercase() }
        } else {
            name
        }
    }


}