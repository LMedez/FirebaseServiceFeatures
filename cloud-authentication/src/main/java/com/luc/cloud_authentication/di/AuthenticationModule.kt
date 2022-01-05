package com.luc.cloud_authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.luc.cloud_authentication.AuthenticationDataSource
import org.koin.dsl.module

val authenticationModule = module{
    single { FirebaseAuth.getInstance() }
    single { AuthenticationDataSource(get()) }
}