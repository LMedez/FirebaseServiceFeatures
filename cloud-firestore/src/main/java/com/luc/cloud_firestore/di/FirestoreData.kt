package com.luc.cloud_firestore.di

import com.google.firebase.firestore.FirebaseFirestore
import com.luc.common.NetworkStatus
import com.luc.common.models.NotificationToken
import com.luc.common.models.User
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.*
import kotlin.random.Random

class FirestoreData(private val firestore: FirebaseFirestore) {

    suspend fun refreshUserNotificationToken(token: String): NetworkStatus<String> {
        return try {
            val data =
                firestore.collection("users").document("dHqwQqswQ9nGh3nocz80").get().await()
                    .toObject(User::class.java)
            val calendar = Calendar.getInstance()
            calendar.time =
                data?.notificationToken?.timestamp ?: return NetworkStatus.Error(
                    null,
                    "User does not exists"
                )

            if (calendar.get(Calendar.MONTH) + 1 < Calendar.getInstance().get(Calendar.MONTH) + 1) {
                firestore.collection("users").document("dHqwQqswQ9nGh3nocz80")
                    .update("notificationToken", NotificationToken(token)).await()
            }
            NetworkStatus.Success("Successfully token refreshed")

        } catch (e: Exception) {
            NetworkStatus.Error(e, "Unknown error")
        }
    }

    suspend fun setCount() {
        firestore.collection("users").document("dHqwQqswQ9nGh3nocz80")
            .update("count", Random.nextInt()).await()
    }
}