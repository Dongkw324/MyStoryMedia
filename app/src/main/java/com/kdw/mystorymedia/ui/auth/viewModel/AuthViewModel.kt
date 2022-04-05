package com.kdw.mystorymedia.ui.auth.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.kdw.mystorymedia.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val repository : AuthRepository,
    private val context : Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

}