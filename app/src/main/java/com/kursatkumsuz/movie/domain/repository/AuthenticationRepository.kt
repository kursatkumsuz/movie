package com.kursatkumsuz.movie.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthenticationRepository {

    suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult>

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult>

    suspend fun isSignedIn() : Boolean
}