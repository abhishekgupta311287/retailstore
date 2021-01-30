package com.abhishek.retailstore.repo

import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.repo.db.IRetailStoreDao

class RetailStoreRepo(private val dao: IRetailStoreDao):IRetailStoreRepo {
    override suspend fun insertOrUpdate(cart: Cart) = dao.insertOrUpdate(cart)

    override suspend fun deleteFromCart(cart: Cart) = dao.deleteFromCart(cart)

    override suspend fun getProductsFromCart(): List<Cart> = dao.getProductsFromCart()
}