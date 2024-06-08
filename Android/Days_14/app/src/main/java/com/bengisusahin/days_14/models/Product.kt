package com.bengisusahin.days_14.models

data class Product(
    val key: String,
    val value: ProductValue
)

data class ProductValue(
    val title: String = "",
    val value: String = "",
    val price: Int = 0
)
