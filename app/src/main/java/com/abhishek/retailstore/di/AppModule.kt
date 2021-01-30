package com.abhishek.retailstore.di

import com.abhishek.retailstore.repo.IRetailStoreRepo
import com.abhishek.retailstore.repo.RetailStoreRepo
import com.abhishek.retailstore.viewmodel.RetailStoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IRetailStoreRepo> { RetailStoreRepo(get()) }
    viewModel { RetailStoreViewModel(get()) }
}