package com.abhishek.retailstore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Category
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.repo.IRetailStoreRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RetailStoreViewModel(private val repo: IRetailStoreRepo) : ViewModel() {
    val selectedProduct: MutableLiveData<Product> = MutableLiveData()
    val cartListLiveData: MutableLiveData<Pair<List<Cart>, Double>> = MutableLiveData()
    val categoriesLiveData: MutableLiveData<List<Category>> = MutableLiveData()

    init {
        populateCategories()
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {

            repo.insertOrUpdate(
                Cart(
                    product
                )
            )

            cartListLiveData.value = fetchCartWithTotalPrice()

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

    private suspend fun fetchCartWithTotalPrice(): Pair<List<Cart>, Double> {
        val productsFromCart = repo.getProductsFromCart()
        val cartTotalPrice = productsFromCart.sumByDouble { it.totalPrice }

        return Pair(productsFromCart, cartTotalPrice)
    }

    private fun populateCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val listType =
                object : TypeToken<ArrayList<Category>>() {}.type
            categoriesLiveData.postValue(Gson().fromJson(prePopulateJsonData(), listType))
        }
    }

    private fun prePopulateJsonData(): String {
        return "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Electronics\",\n" +
                "    \"products\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"image\": " + R.drawable.microwave + ",\n" +
                "        \"name\": \"Microwave Oven\",\n" +
                "        \"price\": 8000\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"image\": " + R.drawable.television + ",\n" +
                "        \"name\": \"Television\",\n" +
                "        \"price\": 30000\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "        \"image\": " + R.drawable.vacuum + ",\n" +
                "        \"name\": \"Vacuum Cleaner\",\n" +
                "        \"price\": 15000\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Furniture\",\n" +
                "    \"products\": [\n" +
                "      {\n" +
                "        \"id\": 4,\n" +
                "        \"image\": " + R.drawable.table + ",\n" +
                "        \"name\": \"Table\",\n" +
                "        \"price\": 3000\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 5,\n" +
                "        \"image\": " + R.drawable.chair + ",\n" +
                "        \"name\": \"Chair\",\n" +
                "        \"price\": 800\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 6,\n" +
                "        \"image\": " + R.drawable.almirah + ",\n" +
                "        \"name\": \"Almirah\",\n" +
                "        \"price\": 10000\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]"
    }
}