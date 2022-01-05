package com.luc.firebaseservice.di

import android.app.Application
import com.luc.cloud_authentication.di.authenticationModule
import com.luc.cloud_firestore.di.firebaseModule
import com.luc.cloud_messaging.di.messagingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(firebaseModule + appModule + messagingModule + authenticationModule)
        }
    }
}