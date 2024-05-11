package com.bengisusahin.days_8.services

import com.bengisusahin.days_8.models.User
import com.bengisusahin.days_8.models.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IDummyService {

    @POST("auth/login")
    fun userLogin(@Body userLogin: UserLogin): Call<User>


}