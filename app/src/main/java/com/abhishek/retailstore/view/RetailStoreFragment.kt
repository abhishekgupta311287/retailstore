package com.abhishek.retailstore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Category
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.view.adapter.CategoryAdapter
import com.abhishek.retailstore.viewmodel.RetailStoreViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RetailStoreFragment : Fragment(R.layout.main_fragment), IProductListener {

    companion object {
        fun newInstance() = RetailStoreFragment()
    }

    private val viewModel by sharedViewModel<RetailStoreViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        categoryRecyclerView.addItemDecoration(
            DividerItemDecoration(
                categoryRecyclerView.context,
                (categoryRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        val categoryAdapter = CategoryAdapter<Category>(this)

        categoryRecyclerView.adapter = categoryAdapter

        viewModel.categoriesLiveData.observe(viewLifecycleOwner, Observer {
            categoryAdapter.categories = it
        })
    }

    override fun onProductSelected(product: Product) {
        viewModel.selectedProduct.value = product
    }

}