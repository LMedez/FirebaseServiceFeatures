plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services")
}

apply("../default-config.gradle")

dependencies {
    implementation( "androidx.core:core-ktx:1.7.0")

    implementation(project(":common"))
    implementation(project(":cloud-messaging"))
    implementation(project(":cloud-firestore"))

    implementation(Libraries.koin)

    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

}