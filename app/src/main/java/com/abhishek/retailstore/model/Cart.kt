package com.abhishek.retailstore.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "cart_", primaryKeys = ["id"])
data class Cart(
    @Embedded
    val product: Product,
    val quantity: Int=1,
    val totalPrice: Double = quantity * product.price
)