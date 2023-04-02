package com.kursatkumsuz.movie.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
) : AuthenticationRepository {

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun isSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}