package com.abhishek.retailstore.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.view.ICartDeleteListener
import com.abhishek.retailstore.view.IProductListener
import com.abhishek.retailstore.view.viewholder.BaseViewHolder
import com.abhishek.retailstore.view.viewholder.CartViewHolder
import kotlinx.android.synthetic.main.cart_item_layout.view.*

class CartAdapter<T>(
    private val selectionListener: IProductListener,
    private val deletionListener: ICartDeleteListener
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var cartProductList:List<Cart> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val view = View.inflate(parent.context, R.layout.cart_item_layout, null)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartProductList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val cart = cartProductList[position]
        holder.bind(cart as T)
        holder.itemView.setOnClickListener {
            selectionListener.onProductSelected(cart.product)
        }
        holder.itemView.deleteCartProduct.setOnClickListener {
            deletionListener.onProductDeleted(cart)
        }
    }

}