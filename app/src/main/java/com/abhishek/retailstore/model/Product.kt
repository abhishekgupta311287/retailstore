package com.abhishek.retailstore.model

import androidx.room.Entity

@Entity(tableName = "product_", primaryKeys = ["id"])
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val image: Int
)