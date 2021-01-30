package com.abhishek.retailstore.repo.db

import com.abhishek.retailstore.model.Cart

interface IRetailStoreDao {
    suspend fun insertOrUpdate(cart: Cart)

    suspend fun deleteFromCart(cart: Cart)

    suspend fun getProductsFromCart():List<Cart>
}