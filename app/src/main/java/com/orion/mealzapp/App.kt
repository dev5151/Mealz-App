package com.orion.mealzapp

import android.app.Application
import com.orion.mealzapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(listOf(AppModule().appModule, AppModule().netModule))
        }
    }
}