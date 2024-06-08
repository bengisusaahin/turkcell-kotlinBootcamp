package com.bengisusahin.days_14

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_14.models.Product
import com.bengisusahin.days_14.models.ProductValue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class MainActivity : AppCompatActivity() {

    lateinit var remoteConfig: FirebaseRemoteConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Firebase DB
        val database = FirebaseDatabase.getInstance().getReference("products")

        // add data
        val key = database.push().key
        val product1= ProductValue("TV", "TV Detail", 20000)
//        database.child(key!!).setValue(product1).addOnCompleteListener {
//            Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener {
//            // failed
//            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
//        }

        // data list
        val arr  = mutableListOf<Product>()
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach {
                        val productValue= it.getValue(ProductValue::class.java)
                        Log.d("productid", it.key.toString())
                        Log.d("productVal", productValue.toString())
                        val product = Product(it.key.toString(), productValue!!)
                        arr.add(product)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        // data list single
        database.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach {
                        Log.d("single", it.toString())
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        // single data
        database.child("-NzsQSMyvx_109uOp3T8").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    Log.d("childData", snapshot.value.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        // update data
        val product2= ProductValue("TV", "TV Detail", 20000)
        database.child("-NzsjhKjsEk1_A0jT0mn").setValue(product2).addOnCompleteListener {
            Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            // failed
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }

        // delete data
        database.child("-NzshdrClH-gG-zs-apA").removeValue().addOnCompleteListener {
            Toast.makeText(this, "Delete success", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            // failed
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }

        // remote config
//        remoteConfig = FirebaseRemoteConfig.getInstance()
//        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
//        fetch()

        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
// Set default values (fallback when values are not available)
        val defaults: Map<String, Any> = mapOf(
            "welcome_message" to "Welcome to our app!",
            // Add more default values here
        )
        firebaseRemoteConfig.setDefaultsAsync(defaults)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Fetch remote configs
                    firebaseRemoteConfig.fetchAndActivate()
                        .addOnCompleteListener { fetchTask ->
                            if (fetchTask.isSuccessful) {
                                // Remote configs fetched and activated
                                val welcomeMessage = firebaseRemoteConfig.getString("welcome_message")
                                // Use welcomeMessage in your app
                                Log.d("welcomeMessage", welcomeMessage )
                            } else {
                                // Fetch failed
                            }
                        }
                } else {
                    // Set defaults failed
                }
            }
    }

    fun fetch(){
        val time:Long = 3600
        remoteConfig.fetch(time).
        addOnCompleteListener {
            if (it.isSuccessful) {
                remoteConfig.activate()

                val backColor = remoteConfig.getString("backgroundColor")
                Log.d("backColor", backColor)
            }
        }
    }
}