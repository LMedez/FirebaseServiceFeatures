package com.luc.common.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class User(val count: Int = 15 ,val notificationToken: NotificationToken = NotificationToken(""))

data class NotificationToken(val token: String = "", @ServerTimestamp val timestamp: Date? = null)