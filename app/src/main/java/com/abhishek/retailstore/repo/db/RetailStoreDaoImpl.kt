package com.abhishek.retailstore.repo.db

import com.abhishek.retailstore.model.Cart

class RetailStoreDaoImpl(private val dao:IRetailStoreDbDao):IRetailStoreDao {
    override suspend fun insertOrUpdate(cart: Cart) = dao.insertOrUpdate(cart)

    override suspend fun deleteFromCart(cart: Cart) =dao.deleteFromCart(cart)

    override suspend fun getProductsFromCart(): List<Cart> = dao.getProductsFromCart()

}