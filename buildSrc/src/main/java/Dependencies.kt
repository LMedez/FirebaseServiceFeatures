object Libraries {
    private object Versions {
        const val firebaseBom = "29.0.3"
        const val firebaseCoroutines = "1.1.1"
        const val koinVersion= "3.1.4"

    }
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.firebaseCoroutines}"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging-ktx"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx"

    const val koin = "io.insert-koin:koin-android:${Versions.koinVersion}"

}