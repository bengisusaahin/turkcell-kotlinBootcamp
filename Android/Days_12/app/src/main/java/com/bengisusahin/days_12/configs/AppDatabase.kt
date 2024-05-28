package com.bengisusahin.days_12.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bengisusahin.days_12.dao.IProductDao
import com.bengisusahin.days_12.entities.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun productDao() : IProductDao

}