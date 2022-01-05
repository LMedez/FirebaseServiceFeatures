package com.luc.firebaseservice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.luc.common.NetworkStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthenticationFragment : Fragment() {

    private val firebaseViewModel: FirebaseViewModel by viewModel()


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
            val email = view.findViewById<TextInputLayout>(R.id.emailInput).editText?.text.toString()
            val password = view.findViewById<TextInputLayout>(R.id.passwordInput).editText?.text.toString()
            firebaseViewModel.signInWithEmailAndPassword(email, password).observe(viewLifecycleOwner) {
                when(it) {
                    is NetworkStatus.Success -> Toast.makeText(requireContext(), it.data.email, Toast.LENGTH_LONG).show()
                    is NetworkStatus.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        view.findViewById<MaterialButton>(R.id.signUpBtn).setOnClickListener {
            val email = view.findViewById<TextInputLayout>(R.id.emailInput).editText?.text.toString()
            val password = view.findViewById<TextInputLayout>(R.id.passwordInput).editText?.text.toString()
            firebaseViewModel.signUpWithEmailAndPassword(email, password).observe(viewLifecycleOwner) {
                when(it) {
                    is NetworkStatus.Success -> Toast.makeText(requireContext(), it.data.email, Toast.LENGTH_LONG).show()
                    is NetworkStatus.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}