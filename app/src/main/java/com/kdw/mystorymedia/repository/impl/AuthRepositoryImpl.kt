package com.kdw.mystorymedia.repository.impl

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kdw.mystorymedia.repository.AuthRepository
import com.kdw.mystorymedia.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl : AuthRepository {

    val firebaseAuth = FirebaseAuth.getInstance()
    val user = FirebaseFirestore.getInstance().collection("user")

    override suspend fun login(email: String, password: String): Resource<AuthResult> {
        TODO()
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