package com.kdw.mystorymedia.ui.auth.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kdw.mystorymedia.R
import com.kdw.mystorymedia.databinding.FragmentRegisterBinding
import com.kdw.mystorymedia.ui.auth.viewModel.AuthViewModel
import com.kdw.mystorymedia.ui.snackBar
import com.kdw.mystorymedia.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding

    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        subscribeToObserver()

        binding.apply {
            loginTxt.setOnClickListener {
                if(findNavController().previousBackStackEntry != null) {
                    findNavController().popBackStack()
                } else {
                    SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                }
            }

            registerBtn.setOnClickListener {
                authViewModel.register(
                    emailTxt.text.toString(),
                    userNameTxt.text.toString(),
                    pwdTxt.text.toString(),
                    repeatPwdTxt.text.toString(),
                    requireContext()
                )
            }
        }
    }

    private fun subscribeToObserver() {
        authViewModel.registerState.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.registerProgressBar.isVisible = false
                snackBar(it)
            },
            onLoading = {
                binding.registerProgressBar.isVisible = true
            }
        ){
            binding.registerProgressBar.isVisible = false
        })
    }
}