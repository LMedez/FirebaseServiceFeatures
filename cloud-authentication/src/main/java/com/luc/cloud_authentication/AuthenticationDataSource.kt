package com.luc.cloud_authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.luc.common.NetworkStatus
import com.luc.common.ProviderType
import com.luc.common.models.UserProfile
import kotlinx.coroutines.tasks.await

class AuthenticationDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): NetworkStatus<UserProfile> {
        return try {
            val data = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            if (data.user == null) return NetworkStatus.Error(
                NullPointerException(),
                "The user is null"
            )
            NetworkStatus.Success(data.user!!.asUserProfile())
        } catch (e: FirebaseAuthException) {
            NetworkStatus.Error(e, e.message ?: "An Error occurred")
        }
    }

    suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): NetworkStatus<UserProfile> {
        return try {
            val data = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()
            if (data.user == null) return NetworkStatus.Error(
                NullPointerException(),
                "The user is null"
            )
            NetworkStatus.Success(data.user!!.asUserProfile())
        } catch (e: FirebaseAuthException) {
            NetworkStatus.Error(e, e.message ?: "An Error occurred")
        }
    }

    fun checkUserLoggedIn(): UserProfile? {
        return if (firebaseAuth.currentUser == null) {
            null
        } else {
            firebaseAuth.currentUser!!.asUserProfile()
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}

fun FirebaseUser.asUserProfile(): UserProfile {
    var provider: ProviderType = ProviderType.BASIC
    if (providerData[0].providerId == "google.com") provider = ProviderType.GOOGLE
    return UserProfile(this.uid, this.email ?: "No email", this.photoUrl, provider)

}