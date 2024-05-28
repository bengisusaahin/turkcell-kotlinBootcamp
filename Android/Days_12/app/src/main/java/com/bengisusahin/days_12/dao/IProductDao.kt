package com.bengisusahin.days_12.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bengisusahin.days_12.entities.Product

@Dao
interface IProductDao {
    @Insert
    fun insert(product: Product) : Long  // return id

    @Delete
    fun delete(product: Product) : Int

    @Update
    fun update(product: Product) : Int

    @Query("SELECT * FROM product")
    fun getAll() : List<Product>

    @Query("SELECT * FROM product WHERE pid = :pid")
    fun getById(pid: Int) : Product

    // optional type a gerek yok bos list döner
    @Query("SELECT * FROM product WHERE title like :title")
    fun searchTitle (title: String) : List<Product>
}