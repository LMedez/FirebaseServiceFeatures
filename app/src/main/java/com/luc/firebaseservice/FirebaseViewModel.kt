package com.luc.firebaseservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.luc.cloud_firestore.di.FirestoreData
import com.luc.cloud_messaging.MessagingData
import com.luc.common.NetworkStatus
import kotlinx.coroutines.launch


class FirebaseViewModel constructor(
    private val firestoreData: FirestoreData,
    private val messagingData: MessagingData
) : ViewModel() {

    fun setCount() = liveData {
        emit(firestoreData.setCount())
    }

    fun subscribeToTopic(topic: String) = liveData {
         emit(messagingData.subscribeToTopic(topic))
    }

    fun getNotificationToken() = liveData { emit(messagingData.getToken()) }

    fun refreshToken(token: String) = viewModelScope.launch {
        firestoreData.refreshUserNotificationToken(token)
    }
}