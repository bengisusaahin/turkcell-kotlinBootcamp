package com.bengisusahin.days_11

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_11.services.ContactService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contactService = ContactService(this)
        // val status = contactService.addContact("Ali", "Bilmem", 30, "red")
        //val status = contactService.addContact("Erdal", "Bilki", 35, "red")
        //Log.d("addContact", "$status")

//        val deleteStatus = contactService.deleteContact(4)
//        Log.d("deleteStatus", "$deleteStatus")
//
//        val updateStatus = contactService.updateContact(11, "Zehra", "Bilir", 30, "blue")
//        // updateStatus = 1 ise güncelleme başarılı olmuştur
//        Log.d("updateStatus", "$updateStatus")
//
//        val arrContacts = contactService.getContacts()
//        for (contact in arrContacts) {
//            Log.d("Contact", contact.toString())
//        }

        /*
        // isminde de soyisminde de ze varsa onu öne alır sıralamada gösterirken
        val arrContacts = contactService.searchContacts("ze")
        for (contact in arrContacts) {
            Log.d("Contact", contact.toString())
        }

         */

        val item =contactService.singleContact("Zehra", "Bilir")
        Log.d("single", item.toString())
    }
}