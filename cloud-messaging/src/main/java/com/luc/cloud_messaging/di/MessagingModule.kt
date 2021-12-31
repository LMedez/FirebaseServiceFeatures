package com.luc.cloud_messaging.di

import com.luc.cloud_messaging.MessagingData
import org.koin.dsl.module

val messagingModule = module{
    single { MessagingData() }
}