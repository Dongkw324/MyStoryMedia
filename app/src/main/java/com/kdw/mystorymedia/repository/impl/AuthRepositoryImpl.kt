package com.kdw.mystorymedia.repository.impl

import com.google.firebase.auth.AuthResult
import com.kdw.mystorymedia.repository.AuthRepository
import com.kdw.mystorymedia.util.Resource

class AuthRepositoryImpl : AuthRepository {
    
    override suspend fun login(email: String, password: String): Resource<AuthResult> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        userName: String,
        email: String,
        password: String
    ): Resource<AuthResult> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPwd(email: String): Resource<AuthResult> {
        TODO("Not yet implemented")
    }

}