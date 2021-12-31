package com.luc.cloud_messaging

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.luc.common.NetworkStatus
import kotlinx.coroutines.tasks.await

class MessagingData {

    suspend fun getToken(): NetworkStatus<String> {
        return try {
            val token = FirebaseMessaging.getInstance().token.await()
            if (token.isNullOrEmpty()) {
                return NetworkStatus.Error(null, "")
            }

            NetworkStatus.Success(token)
        } catch (e: Exception) {
            NetworkStatus.Error(e, "Token null")
        }


    }
}