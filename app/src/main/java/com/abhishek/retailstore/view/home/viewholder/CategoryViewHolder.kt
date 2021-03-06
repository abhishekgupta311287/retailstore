package com.abhishek.retailstore.view.home.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.retailstore.model.Category
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.view.IProductListener
import com.abhishek.retailstore.view.home.adapter.ItemAdapter
import com.abhishek.retailstore.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.category_layout.view.*

class CategoryViewHolder<T>(view: View, private val listener: IProductListener) : BaseViewHolder<T>(view) {

    override fun bind(t: T?) {
        val category = t as Category
        itemView.categoryName.text = category.name

        val linearLayoutManager = LinearLayoutManager(itemView.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        itemView.itemRecyclerView.layoutManager = linearLayoutManager

        val itemAdapter =  ItemAdapter<Product>(listener)
        itemView.itemRecyclerView.adapter = itemAdapter

        itemAdapter.products = category.products

    }

}