package com.abhishek.retailstore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Category
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.ui.main.RetailStoreViewModel
import com.abhishek.retailstore.view.adapter.CartAdapter
import kotlinx.android.synthetic.main.cart_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CartFragment : Fragment(R.layout.cart_fragment), IProductListener, ICartDeleteListener {

    companion object {
        fun newInstance() = CartFragment()
    }

    private val viewModel by sharedViewModel<RetailStoreViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartRecyclerView.addItemDecoration(
            DividerItemDecoration(
                cartRecyclerView.context,
                (cartRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        val cartAdapter = CartAdapter<Category>(this, this)

        cartRecyclerView.adapter = cartAdapter

        viewModel.cartListLiveData.observe(viewLifecycleOwner, Observer {
            cartAdapter.cartProductList = it
        })
    }

    override fun onProductSelected(product: Product) {
        viewModel.selectedProduct.value = product
    }

    override fun onProductDeleted(cart: Cart) {
        viewModel.deleteCartProduct(cart)
    }


}