package com.abhishek.retailstore.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Category
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.view.adapter.CartAdapter
import com.abhishek.retailstore.viewmodel.RetailStoreViewModel
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
            val (list, totalPrice) = it
            val visibility = if (list.isEmpty()) {
                noItemsMsg.visibility = View.VISIBLE
                View.GONE
            } else {
                cartAdapter.cartProductList = list
                cartAdapter.notifyDataSetChanged()
                cartTotalPrice.text = getString(R.string.cart_total_price, totalPrice.toString())

                noItemsMsg.visibility = View.GONE
                View.VISIBLE
            }

            cartRecyclerView.visibility = visibility
            titleLayout.visibility = visibility
            cartTotalPrice.visibility = visibility
        })

    }

    override fun onProductSelected(product: Product) {
        viewModel.selectedProduct.value = product
    }

    override fun onProductDeleted(cart: Cart) {
        viewModel.deleteCartProduct(cart)
        Toast.makeText(
            requireContext(),
            getString(R.string.deleted_frm_cart_msg, cart.product.name),
            Toast.LENGTH_SHORT
        ).show()
    }


}