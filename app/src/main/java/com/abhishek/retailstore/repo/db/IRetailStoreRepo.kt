package com.abhishek.retailstore.repo.db

import com.abhishek.retailstore.model.Cart

interface IRetailStoreRepo {
    suspend fun insertOrUpdate(cart: Cart)

    suspend fun deleteFromCart(cart: Cart)

    suspend fun getProductsFromCart():List<Cart>
}