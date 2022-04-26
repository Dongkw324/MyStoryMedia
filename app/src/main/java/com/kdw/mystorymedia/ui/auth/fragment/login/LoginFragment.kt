package com.kdw.mystorymedia.ui.auth.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kdw.mystorymedia.R
import com.kdw.mystorymedia.databinding.FragmentLoginBinding
import com.kdw.mystorymedia.ui.auth.viewModel.AuthViewModel
import com.kdw.mystorymedia.ui.main.activity.MainActivity
import com.kdw.mystorymedia.ui.snackBar
import com.kdw.mystorymedia.util.EventObserver

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        subscribeObserver()

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

            loginButton.setOnClickListener {
                authViewModel.login(
                    inputId.text.toString(),
                    inputPassword.text.toString(),
                    requireContext()
                )
            }
        }
    }

    private fun subscribeObserver() {
        authViewModel.loginState.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.loginProgressBar.isVisible = false
                snackBar(it)
            },
            onLoading = {
                binding.loginProgressBar.isVisible = true
            }
        ){
            binding.loginProgressBar.isVisible = true
            Intent(requireContext(), MainActivity::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        })
    }
}