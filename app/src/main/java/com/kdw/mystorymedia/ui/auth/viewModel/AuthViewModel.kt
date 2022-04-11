package com.kdw.mystorymedia.ui.auth.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.kdw.mystorymedia.R
import com.kdw.mystorymedia.repository.AuthRepository
import com.kdw.mystorymedia.util.Event
import com.kdw.mystorymedia.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepository : AuthRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _loginState = MutableLiveData<Event<Resource<AuthResult>>>()
    private val _registerState = MutableLiveData<Event<Resource<AuthResult>>>()
    private val _resetState = MutableLiveData<Event<Resource<Void>>>()

    val loginState : LiveData<Event<Resource<AuthResult>>> = _loginState
    val registerState : LiveData<Event<Resource<AuthResult>>> = _registerState
    val resetState : LiveData<Event<Resource<Void>>> = _resetState

    fun login(email: String, password: String, applicationContext: Context) {
        if(email.isEmpty() || password.isEmpty()) {
            val error = applicationContext.getString(R.string.login_error)
            _loginState.postValue(Event(Resource.Error(error)))
        } else {
            _loginState.postValue(Event(Resource.Loading()))
            viewModelScope.launch(dispatcher) {
                val result = authRepository.login(email, password)
                _loginState.postValue(Event(result))
            }
        }
    }

    fun resetPwd(email: String, applicationContext: Context) {
        if(email.isEmpty()) {
            val error = applicationContext.getString(R.string.input_email_err)
            _resetState.postValue(Event(Resource.Error(error)))
        } else {
            _resetState.postValue(Event(Resource.Loading()))
            viewModelScope.launch(dispatcher) {
                val result = authRepository.resetPwd(email)
                _resetState.postValue(Event(result))
            }
        }
    }

    fun register(email: String, userName: String, password: String, repeatPassword: String) {

    }

}