package com.kdw.mystorymedia.ui.auth.fragment.pwd

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kdw.mystorymedia.R
import com.kdw.mystorymedia.databinding.FragmentPwdResetBinding
import com.kdw.mystorymedia.ui.auth.viewModel.AuthViewModel
import com.kdw.mystorymedia.ui.snackBar
import com.kdw.mystorymedia.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PwdResetFragment : Fragment(R.layout.fragment_pwd_reset) {

    private lateinit var binding: FragmentPwdResetBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        binding = FragmentPwdResetBinding.bind(view)

        subscribeToObserver()

        binding.apply {
            backLogin.setOnClickListener {
                findNavController().navigate(
                    PwdResetFragmentDirections.actionPwdResetFragmentToLoginFragment()
                )
            }

            resetPwdBtn.setOnClickListener {
                authViewModel.resetPwd(
                    resetEmailTxt.text.toString(),
                    requireContext()
                )
            }
        }
    }

    private fun subscribeToObserver() {
        authViewModel.resetState.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.resetPwdProgressBar.isVisible = false
                snackBar(it)
            },
            onLoading = {
                binding.resetPwdProgressBar.isVisible = true
            }
        ){
            binding.resetPwdProgressBar.isVisible = false
        })
    }
}