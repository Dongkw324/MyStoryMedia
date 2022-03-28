package com.kdw.mystorymedia.repository.impl

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kdw.mystorymedia.model.User
import com.kdw.mystorymedia.repository.AuthRepository
import com.kdw.mystorymedia.util.Resource
import com.kdw.mystorymedia.util.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepositoryImpl : AuthRepository {

    val firebaseAuth = FirebaseAuth.getInstance()
    val users = FirebaseFirestore.getInstance().collection("users")

    override suspend fun login(email: String, password: String): Resource<AuthResult> {
       return withContext(Dispatchers.IO) {
            safeCall {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(result)
            }
        }
    }

    override suspend fun register(
        userName: String,
        email: String,
        password: String
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                val uid = result.user?.uid!!
                val user = User(uid, userName)
                users.document(uid).set(user).await()
                Resource.Success(result)

            }
        }
    }

    override suspend fun resetPwd(email: String): Resource<Void> {
        return withContext(Dispatchers.IO) {
            try {
                val result = firebaseAuth.sendPasswordResetEmail(email).await()
                Resource.Success(result)
            } catch(e: Exception) {
                Resource.Error("이미 이메일을 보냈습니다", null)
            }
        }
    }

}