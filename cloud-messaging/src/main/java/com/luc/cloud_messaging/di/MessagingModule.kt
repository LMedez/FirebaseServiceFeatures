package com.luc.cloud_messaging.di

import com.google.firebase.messaging.FirebaseMessaging
import com.luc.cloud_messaging.MessagingDataSource
import org.koin.dsl.module

val messagingModule = module{
    single { FirebaseMessaging.getInstance() }
    single { MessagingDataSource(get()) }
}