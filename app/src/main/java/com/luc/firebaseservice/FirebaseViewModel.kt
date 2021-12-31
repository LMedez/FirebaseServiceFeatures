package com.luc.firebaseservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.luc.cloud_firestore.di.FirestoreData
import com.luc.cloud_messaging.MessagingData


class FirebaseViewModel constructor(private val firestoreData: FirestoreData, private val messagingData: MessagingData ) : ViewModel() {

    fun setCount() = liveData {
        emit(firestoreData.setCount())
    }

    fun getNotificationToken() = liveData { emit(messagingData.getToken()) }

    fun refreshToken(token: String) = liveData {
        emit(firestoreData.refreshUserNotificationToken(token))
    }
}