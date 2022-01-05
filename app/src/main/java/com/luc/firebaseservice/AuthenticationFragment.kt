package com.luc.firebaseservice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.luc.common.NetworkStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthenticationFragment : Fragment() {

    private val firebaseViewModel: FirebaseViewModel by viewModel()
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.signInBtn).setOnClickListener {
            val email =
                view.findViewById<TextInputLayout>(R.id.emailInput).editText?.text.toString()
            val password =
                view.findViewById<TextInputLayout>(R.id.passwordInput).editText?.text.toString()
            firebaseViewModel.signInWithEmailAndPassword(email, password)
                .observe(viewLifecycleOwner) {
                    when (it) {
                        is NetworkStatus.Success -> Toast.makeText(
                            requireContext(),
                            it.data.email,
                            Toast.LENGTH_LONG
                        ).show()
                        is NetworkStatus.Error -> Toast.makeText(
                            requireContext(),
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        view.findViewById<MaterialButton>(R.id.signUpBtn).setOnClickListener {
            val email =
                view.findViewById<TextInputLayout>(R.id.emailInput).editText?.text.toString()
            val password =
                view.findViewById<TextInputLayout>(R.id.passwordInput).editText?.text.toString()
            firebaseViewModel.signUpWithEmailAndPassword(email, password)
                .observe(viewLifecycleOwner) {
                    when (it) {
                        is NetworkStatus.Success -> Toast.makeText(
                            requireContext(),
                            it.data.email,
                            Toast.LENGTH_LONG
                        ).show()
                        is NetworkStatus.Error -> Toast.makeText(
                            requireContext(),
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        view.findViewById<SignInButton>(R.id.signInGoogleButton).setOnClickListener {
            getContent.launch(googleSignInClient.signInIntent)
        }
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK)
                onActivityResult(CODE_REQUEST, result)
        }

    private fun onActivityResult(requestCode: Int, result: ActivityResult) {

        val intent = result.data

        when (requestCode) {
            CODE_REQUEST -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)
                    if (account != null) {
                        firebaseViewModel.signInWithGoogle(account.idToken!!)
                            .observe(viewLifecycleOwner) {
                                when (it) {
                                    is NetworkStatus.Success ->
                                        Toast.makeText(
                                            requireContext(),
                                            "Email:  ${it.data.email}, Provider: ${it.data.providerType.name}",
                                            Toast.LENGTH_LONG
                                        ).show()


                                    is NetworkStatus.Error -> Toast.makeText(
                                        requireContext(),
                                        it.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    }
                } catch (e: ApiException) {
                    Log.d("test", e.toString())

                    // Google Sign In failed, update UI appropriately
                }
            }
        }
    }

}

const val CODE_REQUEST = 12