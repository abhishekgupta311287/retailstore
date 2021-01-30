package com.abhishek.retailstore.view.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.retailstore.R
import com.abhishek.retailstore.model.Category
import com.abhishek.retailstore.view.IProductListener
import com.abhishek.retailstore.view.viewholder.BaseViewHolder
import com.abhishek.retailstore.view.home.viewholder.CategoryViewHolder

class CategoryAdapter<T>(private val listener: IProductListener) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var categories:List<Category> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val view = View.inflate(parent.context, R.layout.category_layout, null)
        return CategoryViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(categories[position] as T)
    }

}