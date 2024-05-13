package com.bengisusahin.days_8

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bengisusahin.days_8.configs.ApiClient
import com.bengisusahin.days_8.models.User
import com.bengisusahin.days_8.models.UserLogin
import com.bengisusahin.days_8.services.IDummyService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {

//    lateinit var sharedPreferences: SharedPreferences
//    lateinit var editor: Editor

    lateinit var iDummyService : IDummyService
    lateinit var txtUserName: TextView
    lateinit var txtName : EditText
    lateinit var txtPassword : EditText
    lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        txtUserName = findViewById(R.id.txtUserName)
        txtName = findViewById(R.id.txtName)
        txtPassword = findViewById(R.id.txtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        //sharedPreferences = getSharedPreferences("customer", MODE_PRIVATE)
        //editor = sharedPreferences.edit()
        SharedPrefHelper.init(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iDummyService = ApiClient.getClient().create(IDummyService::class.java)

       // val userLogin = UserLogin("kminchelle", "0lelplR")

        btnLogin.setOnClickListener {
            val name = txtName.text.toString()
            val password = txtPassword.text.toString()
            val userLogin = UserLogin(name,password)
            //async
            iDummyService.userLogin(userLogin).enqueue(object: Callback<User> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<User>, res: Response<User>) {
                    // Servis işlemi başarılı
                    Log.d("status code", res.isSuccessful.toString())
                    Log.d("user", res.body().toString())
                    val dt = res.body()
                    dt?.let {
                        //val gson = Gson()
                        //val stUser = gson.toJson(it)
                        txtUserName.text = "${it.firstName} ${it.lastName}"
                        //editor.putString("token", it.token)
                        //editor.putString("name", "${it.firstName} ${it.lastName}")
                        // elimizde bir string oldu ama ona ait bilgiler json formatında tutuldu
                        //editor.putString("user",stUser)
                        // loginden sonra kullanıcının kaydı yapıldı
                        SharedPrefHelper.setUser(it)
                        //editor.commit()
                    }
                }

                override fun onFailure(call: Call<User>, th: Throwable) {
                    // işlem sırasında bir hata oluştu!
                    Log.d("Login error", "onFailure: " + th.message)
                }
            })
        }


        /*
        // sync
        val rn = Runnable {
            try {
                val res = iDummyService.userLogin(userLogin).execute()
                if (res.isSuccessful){
                    Log.d("mesaj", res.message())
                    val dt = res.body()
                    dt?.let {
                        Log.d("execute", it.toString())
                    }
                }
            }catch (e:Exception){
                Log.e("e", e.toString())
            }
        }

        val th = Thread(rn)
        th.start()



        // bu satır daha önce çalıştı
        Log.d("line", "this line call")

         */

/*        // class ı object e dönüştürdük
        // val api = ApiClient()
        val dt1 = ApiClient.getClient()
        Log.d("dt1", dt1.hashCode().toString())
        val dt2 = ApiClient.getClient()
        Log.d("dt2", dt2.hashCode().toString())
        val dt3 = ApiClient.getClient()
        Log.d("dt3", dt3.hashCode().toString())

        call()

 */


    }

/*    fun call(){
        // val api = ApiClient()
        val dt4 = ApiClient.getClient()
        Log.d("dt4", dt4.hashCode().toString())
    }

 */
}