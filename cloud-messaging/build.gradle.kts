plugins {
    id ("com.android.library")
    id ("kotlin-android")
}

apply("../default-config.gradle")

dependencies {
    implementation( "androidx.core:core-ktx:1.7.0")

    implementation(project(":common"))

    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseAnalytics)
    implementation(Libraries.firebaseMessaging)
    implementation(Libraries.firebaseCoroutines)
    implementation(Libraries.koin)


}