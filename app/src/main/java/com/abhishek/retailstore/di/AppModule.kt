package com.abhishek.retailstore.di

import com.abhishek.retailstore.repo.db.IRetailStoreRepo
import com.abhishek.retailstore.repo.db.RetailStoreRepo
import com.abhishek.retailstore.ui.main.RetailStoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IRetailStoreRepo> { RetailStoreRepo(get()) }
    viewModel { RetailStoreViewModel(get()) }
}