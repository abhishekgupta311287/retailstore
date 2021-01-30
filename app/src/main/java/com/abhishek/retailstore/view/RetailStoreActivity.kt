package com.abhishek.retailstore.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.abhishek.retailstore.R
import com.abhishek.retailstore.ui.main.RetailStoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RetailStoreActivity : AppCompatActivity() {

    private val viewModel: RetailStoreViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

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

        viewModel.cartListLiveData.observe(this, Observer {

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.showCart) {
            viewModel.fetchCart()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CartFragment.newInstance())
                .addToBackStack("Fragment")
                .commit()
//            item.setIcon(R.drawable.shopping_cart_filled)
        }
        return super.onOptionsItemSelected(item)
    }
}