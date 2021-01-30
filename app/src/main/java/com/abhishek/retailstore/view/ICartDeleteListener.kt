package com.abhishek.retailstore.view

import com.abhishek.retailstore.model.Cart

interface ICartDeleteListener {
    fun onProductDeleted(cart: Cart)
}