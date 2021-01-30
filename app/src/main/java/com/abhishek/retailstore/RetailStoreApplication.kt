package com.abhishek.retailstore

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.abhishek.retailstore.di.appModule
import com.abhishek.retailstore.di.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RetailStoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RetailStoreApplication)
            modules(
                listOf(
                    dbModule,
                    appModule
                )
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)

    }
}