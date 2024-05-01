package com.bengisusahin.days_5

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_5.components.CustomToast

class MainActivity : AppCompatActivity() {

    lateinit var btnAlert: Button
    lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAlert = findViewById(R.id.btnAlert)
        btnConfirm = findViewById(R.id.btnConfirm)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Benim özel bir view im var. Bu view in buraya taşınarak kulanıma uygun bir hale gelmesini istiyorum.
        // Bu görüntüye tabi tutulacak olan view in değişmesi için kullanılacak olan viewdir.
        //val custom_alert_view = layoutInflater.inflate(R.layout.custom_alert, null)

        btnAlert.setOnClickListener {
            // Toast.makeText(this, "Alert !!!", Toast.LENGTH_SHORT).show()

//            val customToast = Toast(this)
//            customToast.duration = Toast.LENGTH_LONG
//            customToast.setGravity(Gravity.CENTER_VERTICAL, 0,0)
//            customToast.view = custom_alert_view
//            customToast.show()

            CustomToast(this,"Error","Customer Data Error!",Toast.LENGTH_LONG).show()
        }

        btnConfirm.setOnClickListener {
            // Asıl amaçları o an yaşayan appCompatActivity nin nesnesine ulaşmaktır.( this )
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete")
            builder.setMessage("Are you sure?")

            builder.setPositiveButton("Yes") { dialog, which ->
                Log.d("builder", "Yes Click")
            }

            builder.setNegativeButton("No" ){ dialog, which ->
                Log.d("builder", "No Click")
            }

            builder.setNeutralButton("Cancel" ){ dialog, which ->
                Log.d("builder", "Cancel Click")
            }

            builder.setCancelable(false)
            val alert = builder.create()
            alert.show()
        }
    }
}