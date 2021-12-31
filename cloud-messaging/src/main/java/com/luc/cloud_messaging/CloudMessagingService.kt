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

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.

    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d(TAG, p0)

    }
}

const val TAG = "FIREBASE-MESSAGING"