package com.luc.cloud_messaging

import com.google.firebase.messaging.FirebaseMessaging
import com.luc.common.NetworkStatus
import kotlinx.coroutines.tasks.await

class MessagingData(private val firebaseMessaging: FirebaseMessaging) {

    suspend fun getToken(): NetworkStatus<String> {
        return try {
            val token = firebaseMessaging.token.await()
            if (token.isNullOrEmpty()) {
                return NetworkStatus.Error(null, "Token is null or empty")
            }
            NetworkStatus.Success("Successfully getting the token: $token")
        } catch (e: Exception) {
            NetworkStatus.Error(e, e.message ?: "")
        }
    }

    suspend fun subscribeToTopic(topic: String): NetworkStatus<String> {
        return try {
            firebaseMessaging.subscribeToTopic(topic).await()
            NetworkStatus.Success("Successfully subscribed to topic: $topic")
        } catch (e: Exception) {
            NetworkStatus.Error(e, e.message ?: "Error")
        }
    }
}