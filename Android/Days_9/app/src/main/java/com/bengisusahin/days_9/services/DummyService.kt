package com.bengisusahin.days_9.services

import Products
import retrofit2.Call
import retrofit2.http.GET


interface IDummyService {
    @GET("products")
    fun getProducts(): Call<Products>
}