package com.luc.firebaseservice.di

import com.luc.firebaseservice.FirebaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModel for Detail View
    viewModel { FirebaseViewModel(get(), get()) }

}