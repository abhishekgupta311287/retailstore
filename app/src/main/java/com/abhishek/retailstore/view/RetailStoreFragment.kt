package com.abhishek.retailstore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Category
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.ui.main.RetailStoreViewModel
import com.abhishek.retailstore.view.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class RetailStoreFragment : Fragment(R.layout.main_fragment), IProductListener {

    companion object {
        fun newInstance() = RetailStoreFragment()
    }

    private lateinit var viewModel: RetailStoreViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(RetailStoreViewModel::class.java)

        val categories = ArrayList<Category>()

        val product = Product(1, "Microwave Oven", 8000.0, R.drawable.microwave)
        val category = Category(
            1,
            "Electronics",
            arrayListOf(
                product,
                product.copy(id = 2, name = "Television", price = 30000.0, image = R.drawable.television),
                product.copy(id = 3, name = "Vacuum Cleaner", price = 15000.0, image = R.drawable.vacuum)
            )
        )

        categories.add(category)
        categories.add(
            category.copy(
                id = 2,
                name = "Furniture",
                products = arrayListOf(
                    product.copy(id = 4, name = "Table", price = 3000.0, image = R.drawable.table),
                    product.copy(id = 5, name = "Chair", price = 800.0, image = R.drawable.chair),
                    product.copy(id = 6, name = "Almirah", price = 10000.0, image = R.drawable.almirah)
                )
            )
        )

        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        categoryRecyclerView.addItemDecoration(
            DividerItemDecoration(
                categoryRecyclerView.context,
                (categoryRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        val categoryAdapter = CategoryAdapter<Category>(this)

        categoryRecyclerView.adapter = categoryAdapter
        categoryAdapter.categories = categories
    }

    override fun onProductSelected(product: Product) {
        viewModel.selectedProduct.value = product
    }

}