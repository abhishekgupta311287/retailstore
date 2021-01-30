package com.abhishek.retailstore.model

data class Category(
    val id: Int,
    val name: String,
    val products: List<Product>
)