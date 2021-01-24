package com.abhishek.retailstore.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abhishek.retailstore.R

class RetailStoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RetailStoreFragment.newInstance())
                    .commitNow()
        }
    }
}