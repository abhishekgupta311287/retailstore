package com.abhishek.retailstore.view.viewholder

import android.view.View
import com.abhishek.retailstore.model.Cart
import kotlinx.android.synthetic.main.cart_item_layout.view.*

class CartViewHolder<T>(view: View) : BaseViewHolder<T>(view) {

    override fun bind(t: T?) {
        val cart = t as Cart
        itemView.cartProductName.text = cart.product.name
        itemView.cartProductPrice.text = cart.product.price.toString()
        itemView.cartProductQuantity.text = cart.quantity.toString()
        itemView.cartProductTotalPrice.text = cart.totalPrice.toString()
    }

}