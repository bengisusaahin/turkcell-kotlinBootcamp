package com.bengisusahin.days_9.configs

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val Base_URL = "https://dummyjson.com/"
    private val client = OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    // bunu da yapabilirsin ama dışardan erişip başlatılabilir ve null girebilir sıkıntı olabilir
    //lateinit var retrofit: Retrofit

    fun getClient(): Retrofit {
//        if (retrofit == null) {
//            retrofit = Retrofit
//                .Builder()
//                .baseUrl(Base_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
        // as -> bu kesinlikle Rerofittir optional değildir diyoruz
        // return retrofit as Retrofit
        return retrofit
    }
}