package com.luc.cloud_firestore.di

import com.google.firebase.firestore.FirebaseFirestore
import com.luc.cloud_firestore.FirestoreData
import org.koin.dsl.module

val firebaseModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirestoreData(get()) }
}