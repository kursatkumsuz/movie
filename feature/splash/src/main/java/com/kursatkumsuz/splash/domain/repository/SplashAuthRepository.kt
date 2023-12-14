package com.kursatkumsuz.splash.domain.repository

interface SplashAuthRepository {
    suspend fun isSignedIn(): Boolean
}