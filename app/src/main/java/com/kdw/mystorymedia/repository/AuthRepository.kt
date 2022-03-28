package com.kdw.mystorymedia.repository

import com.google.firebase.auth.AuthResult
import com.kdw.mystorymedia.util.Resource

interface AuthRepository {

    suspend fun login(email: String, password: String) : Resource<AuthResult>
    suspend fun register(userName: String, email: String, password: String) : Resource<AuthResult>
    suspend fun resetPwd(email: String) : Resource<Void>
}