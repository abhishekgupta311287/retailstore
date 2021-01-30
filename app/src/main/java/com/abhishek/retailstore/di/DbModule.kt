package com.abhishek.retailstore.di

import androidx.room.Room
import com.abhishek.retailstore.repo.db.IRetailStoreDao
import com.abhishek.retailstore.repo.db.RetailStoreDaoImpl
import com.abhishek.retailstore.repo.db.RetailStoreDb
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(get(), RetailStoreDb::class.java, "retail_store.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<IRetailStoreDao> { RetailStoreDaoImpl((get() as RetailStoreDb).retailStoreDao())}
}