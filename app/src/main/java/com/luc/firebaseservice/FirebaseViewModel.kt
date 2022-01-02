package com.luc.firebaseservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.luc.cloud_firestore.FirestoreData
import com.luc.cloud_messaging.MessagingData
import com.luc.common.NetworkStatus
import kotlinx.coroutines.launch


class FirebaseViewModel constructor(
    private val firestoreData: FirestoreData,
    private val messagingData: MessagingData
) : ViewModel() {

    fun subscribeToTopic(topic: String) = liveData {
        when (val result = messagingData.subscribeToTopic(topic)) {
            is NetworkStatus.Success -> {
                emit(firestoreData.addTopic(topic))
            }
            else -> emit(result)
        }
    }

    fun addTopic(topic: String) = liveData {
        emit(firestoreData.addTopic(topic))
    }

    fun getNotificationToken() = liveData { emit(messagingData.getToken()) }

    fun refreshToken(token: String) = viewModelScope.launch {
        firestoreData.refreshUserNotificationToken(token)
    }
}