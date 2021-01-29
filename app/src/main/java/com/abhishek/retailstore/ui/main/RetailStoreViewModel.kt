package com.abhishek.retailstore.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.retailstore.model.Product

class RetailStoreViewModel : ViewModel() {
    val selectedProduct:MutableLiveData<Product> = MutableLiveData()
}