package com.kursatkumsuz.signup.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.kursatkumsuz.signup.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
) : AuthenticationRepository {
    override suspend fun getUserUid(): String {
        var uid = ""
        firebaseAuth.currentUser?.uid?.let {
            uid = it
        }
        return uid
    }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }


}