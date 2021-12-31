package com.luc.cloud_firestore.di

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val firebaseModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirestoreData(get()) }
}