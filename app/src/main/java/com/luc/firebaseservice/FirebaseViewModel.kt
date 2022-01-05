package com.luc.firebaseservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.luc.cloud_authentication.AuthenticationDataSource
import com.luc.cloud_firestore.FirestoreData
import com.luc.cloud_messaging.MessagingDataSource
import com.luc.common.NetworkStatus
import kotlinx.coroutines.launch


class FirebaseViewModel constructor(
    private val firestoreData: FirestoreData,
    private val messagingDataSource: MessagingDataSource,
    private val authenticationDataSource: AuthenticationDataSource
) : ViewModel() {

    fun subscribeToTopic(topic: String) = liveData {
        when (val result = messagingDataSource.subscribeToTopic(topic)) {
            is NetworkStatus.Success -> {
                emit(firestoreData.addTopic(topic))
            }
            else -> emit(result)
        }
    }

    fun addTopic(topic: String) = liveData {
        emit(firestoreData.addTopic(topic))
    }

    fun getNotificationToken() = liveData { emit(messagingDataSource.getToken()) }

    fun refreshToken(token: String) = viewModelScope.launch {
        firestoreData.refreshUserNotificationToken(token)
    }

    fun signUpWithEmailAndPassword(email: String, password: String) = liveData {
        emit(authenticationDataSource.signUpWithEmailAndPassword(email, password))
    }

    fun signInWithEmailAndPassword(email: String, password: String) = liveData {
        emit(authenticationDataSource.signInWithEmailAndPassword(email, password))
    }

    fun signInWithGoogle(token: String) =
        liveData {
            emit(authenticationDataSource.signInWithGoogle(token))
        }
}