package com.luc.cloud_messaging

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CloudMessagingService : FirebaseMessagingService() {

    companion object {
        fun getToken() {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                // Get new FCM registration token
                val token = task.result
                // Log and toast
                Log.d(TAG, token)
            })
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Looper.prepare()
        Handler().post {
            Log.d(TAG, remoteMessage.notification?.title ?: "is null")
            Toast.makeText(baseContext, remoteMessage.notification?.title, Toast.LENGTH_LONG).show()
        }
        Looper.loop()
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}

const val TAG = "FIREBASE-MESSAGING"