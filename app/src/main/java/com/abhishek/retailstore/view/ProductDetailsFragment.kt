package com.abhishek.retailstore.view

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.abhishek.retailstore.R
import com.abhishek.retailstore.ui.main.RetailStoreViewModel
import kotlinx.android.synthetic.main.product_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductDetailsFragment : Fragment(R.layout.product_details_fragment) {

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }

    private val viewModel by sharedViewModel<RetailStoreViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.selectedProduct.observe(viewLifecycleOwner, Observer {
            productDetailsName.text = it.name
            productDetailsPrice.text = it.price.toString()
            productDetailsImage.setImageDrawable(ResourcesCompat.getDrawable(resources, it.image, null))
        })

        addToCartBtn.setOnClickListener {
            viewModel.addToCart(viewModel.selectedProduct.value!!)
        }
    }


}