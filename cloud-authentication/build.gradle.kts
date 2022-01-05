plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply("../default-config.gradle")

dependencies {
    implementation( "androidx.core:core-ktx:1.7.0")
    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseAuth)
    implementation(Libraries.firebaseCoroutines)

    implementation(project(":common"))

    implementation(Libraries.koin)
}