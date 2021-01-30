package com.abhishek.retailstore.view.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.view.IProductListener
import com.abhishek.retailstore.view.viewholder.BaseViewHolder
import com.abhishek.retailstore.view.home.viewholder.ItemViewHolder

class ItemAdapter<T>(private val listener: IProductListener) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var products:List<Product> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val view = View.inflate(parent.context, R.layout.item_layout, null)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val product = products[position]
        holder.bind(product as T)
        holder.itemView.setOnClickListener {
            listener.onProductSelected(product)
        }
    }

}