package com.abhishek.retailstore.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Product

@Database(entities = [Cart::class, Product::class], version = 1, exportSchema = false)
abstract class RetailStoreDb : RoomDatabase() {

    abstract fun retailStoreDao(): IRetailStoreDbDao
}