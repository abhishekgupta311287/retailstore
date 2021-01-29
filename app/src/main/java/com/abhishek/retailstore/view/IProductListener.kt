package com.abhishek.retailstore.view

import com.abhishek.retailstore.model.Product

interface IProductListener {
    fun onProductSelected(product: Product)
}