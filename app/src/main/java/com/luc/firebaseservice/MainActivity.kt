package com.luc.firebaseservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.luc.common.NetworkStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val firebaseViewModel: FirebaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseViewModel.getNotificationToken().observe(this) {
            when (it) {
                is NetworkStatus.Success -> {
                    firebaseViewModel.refreshToken(it.data)
                }
                is NetworkStatus.Error -> Log.d("tests", it.exception.toString())
            }
        }

        firebaseViewModel.setCount().observe(this) {}

        firebaseViewModel.subscribeToTopic("news").observe(this) {
            when (it) {
                is NetworkStatus.Success -> Log.d(TAG, it.data)
                is NetworkStatus.Error -> Log.d(TAG, it.message)
            }
        }

    }
}

const val TAG = "tests"
