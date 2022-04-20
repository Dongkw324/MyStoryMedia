package com.kdw.mystorymedia.ui.auth.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kdw.mystorymedia.R
import com.kdw.mystorymedia.databinding.FragmentRegisterBinding
import com.kdw.mystorymedia.ui.auth.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding

    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        binding.apply {
            loginTxt.setOnClickListener {
                if(findNavController().previousBackStackEntry != null) {
                    findNavController().popBackStack()
                } else {
                    SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                }
            }
        }
    }
}