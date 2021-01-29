package com.abhishek.retailstore.model

import androidx.room.Entity

@Entity(tableName = "category_products_", primaryKeys = ["id"])
data class Category(
    val id: Int,
    val name: String,
    val products: List<Product>
)