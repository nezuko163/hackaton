package com.nezuko.hackaton

import android.app.Application
import android.content.Context
import com.nezuko.hackaton.di.coroutinesModule
import com.nezuko.hackaton.di.dispatchersModule
import com.nezuko.hackaton.di.domainModule
import com.nezuko.hackaton.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.binds
import org.koin.dsl.module

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            module {
                single { this@KoinApplication } binds arrayOf(Context::class, Application::class)
            }

            androidLogger(Level.DEBUG)
            modules(coroutinesModule, dispatchersModule, domainModule, viewModelModule)
        }
    }
}