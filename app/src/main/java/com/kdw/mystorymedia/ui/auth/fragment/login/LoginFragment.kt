package com.kdw.mystorymedia.ui.auth.fragment.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kdw.mystorymedia.R
import com.kdw.mystorymedia.databinding.FragmentLoginBinding
import com.kdw.mystorymedia.ui.auth.viewModel.AuthViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        binding.apply {
            resetTextBtn.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToPwdResetFragment()
                )
            }

            registerTextBtn.setOnClickListener {
                if(findNavController().previousBackStackEntry != null) {
                    findNavController().popBackStack()
                } else {
                    LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
                }
            }
        }
    }
}