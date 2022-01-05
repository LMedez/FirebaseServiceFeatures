package com.luc.cloud_firestore

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.luc.common.NetworkStatus
import com.luc.common.models.NotificationToken
import com.luc.common.models.User
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.Exception
import kotlin.random.Random

class FirestoreData(private val firestore: FirebaseFirestore) {

    suspend fun refreshUserNotificationToken(token: String): NetworkStatus<String> {
        return try {
            val data =
                firestore.collection(COLLECTION).document("dHqwQqswQ9nGh3nocz80").get().await()
                    .toObject(User::class.java)
            val calendar = Calendar.getInstance()
            calendar.time =
                data?.notificationToken?.timestamp ?: return NetworkStatus.Error(
                    null,
                    "User does not exists"
                )

            if (calendar.get(Calendar.MONTH) + 1 < Calendar.getInstance().get(Calendar.MONTH) + 1) {
                firestore.collection(COLLECTION).document("dHqwQqswQ9nGh3nocz80")
                    .update("notificationToken", NotificationToken(token)).await()
            }
            NetworkStatus.Success("Successfully token refreshed")

        } catch (e: Exception) {
            NetworkStatus.Error(e, "Unknown error")
        }
    }

    suspend fun addTopic(topic: String): NetworkStatus<String> {
        return try {
            firestore.collection(COLLECTION).document("dHqwQqswQ9nGh3nocz80")
                .update("topics", FieldValue.arrayUnion(topic)).await()
            NetworkStatus.Success("New topic $topic added successfully")

        } catch (e: Exception) {
            NetworkStatus.Error(e, e.message.toString())
        }
    }
}

const val COLLECTION = "users"