package com.abhishek.retailstore.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abhishek.retailstore.R
import com.abhishek.retailstore.ui.main.RetailStoreViewModel

class RetailStoreFragment : Fragment() {

    companion object {
        fun newInstance() = RetailStoreFragment()
    }

    private lateinit var viewModel: RetailStoreViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RetailStoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}