package com.luc.firebaseservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.luc.cloud_messaging.CloudMessagingService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get token to send notification only to this device
        CloudMessagingService.getToken()
    }
}

