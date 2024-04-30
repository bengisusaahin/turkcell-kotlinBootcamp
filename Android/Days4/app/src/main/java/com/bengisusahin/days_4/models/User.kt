package com.bengisusahin.days_4.models

import java.io.Serializable

// User artık hem User türünde hem Serializable türünde
data class User(
    val id: Int,
    val userName: String,
    val password: String
) : Serializable
