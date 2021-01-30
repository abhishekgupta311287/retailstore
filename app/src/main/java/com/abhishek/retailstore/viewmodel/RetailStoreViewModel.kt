package com.abhishek.retailstore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.repo.db.IRetailStoreRepo
import kotlinx.coroutines.launch

class RetailStoreViewModel(private val repo: IRetailStoreRepo) : ViewModel() {
    val selectedProduct: MutableLiveData<Product> = MutableLiveData()
    val cartListLiveData: MutableLiveData<Pair<List<Cart>, Double>> = MutableLiveData()

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
            cartListLiveData.value = fetchCartWithTotalPrice()
        }
    }

    fun deleteCartProduct(cart: Cart) {
        viewModelScope.launch {
            repo.deleteFromCart(cart)
            cartListLiveData.value = fetchCartWithTotalPrice()
        }
    }

    private suspend fun fetchCartWithTotalPrice():Pair<List<Cart>, Double> {
        val productsFromCart = repo.getProductsFromCart()
        val cartTotalPrice = productsFromCart.sumByDouble { it.totalPrice }

        return Pair(productsFromCart, cartTotalPrice)
    }
}