package com.kursatkumsuz.splash.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.kursatkumsuz.splash.domain.repository.SplashAuthRepository

class SplashAuthRepositoryImpl (private val firebaseAuth: FirebaseAuth): SplashAuthRepository {

    override suspend fun isSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}