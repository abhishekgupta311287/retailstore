package com.abhishek.retailstore.view.viewholder

import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.abhishek.retailstore.model.Product
import kotlinx.android.synthetic.main.item_layout.view.*

class ItemViewHolder<T>(view: View) : BaseViewHolder<T>(view) {

    override fun bind(t: T?) {
        val product = t as Product
        itemView.productName.text = product.name
        itemView.productPrice.text = product.price.toString()
        itemView.productImage.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, product.image, null))
    }

}