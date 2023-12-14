package com.kursatkumsuz.signup.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthenticationRepository {
    suspend fun getUserUid(): String
    suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult>
}