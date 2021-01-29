package com.abhishek.retailstore.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abhishek.retailstore.R
import com.abhishek.retailstore.ui.main.RetailStoreViewModel

class RetailStoreActivity : AppCompatActivity() {

    private lateinit var viewModel: RetailStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProviders.of(this).get(RetailStoreViewModel::class.java)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RetailStoreFragment.newInstance())
                    .commit()
        }

        viewModel.selectedProduct.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductDetailsFragment.newInstance())
                .addToBackStack("RetailStoreFragment")
                .commit()
        })
    }
}