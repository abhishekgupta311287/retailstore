package com.abhishek.retailstore.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.repo.db.IRetailStoreRepo
import kotlinx.coroutines.launch

class RetailStoreViewModel(private val repo: IRetailStoreRepo) : ViewModel() {
    val selectedProduct: MutableLiveData<Product> = MutableLiveData()
    val cartListLiveData: MutableLiveData<List<Cart>> = MutableLiveData()

    fun addToCart(product: Product) {
        viewModelScope.launch {

            repo.insertOrUpdate(
                Cart(
                    product
                )
            )
        }

    }

    fun fetchCart() {
        viewModelScope.launch {
            cartListLiveData.value = repo.getProductsFromCart()
        }
    }

    fun deleteCartProduct(cart: Cart) {
        viewModelScope.launch {
            repo.deleteFromCart(cart)
            cartListLiveData.value = repo.getProductsFromCart()
        }
    }
}