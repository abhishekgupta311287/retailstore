package com.abhishek.retailstore.view

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Category
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.ui.main.RetailStoreViewModel
import com.abhishek.retailstore.view.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.product_details_fragment.*

class ProductDetailsFragment : Fragment(R.layout.product_details_fragment) {

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }

    private lateinit var viewModel: RetailStoreViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(RetailStoreViewModel::class.java)

        viewModel.selectedProduct.observe(viewLifecycleOwner, Observer {
            productDetailsName.text = it.name
            productDetailsPrice.text = it.price.toString()
            productDetailsImage.setImageDrawable(ResourcesCompat.getDrawable(resources, it.image, null))
        })

        addToCartBtn.setOnClickListener {

        }
    }


}