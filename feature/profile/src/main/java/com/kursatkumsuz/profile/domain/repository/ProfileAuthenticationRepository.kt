package com.kursatkumsuz.profile.domain.repository

interface ProfileAuthenticationRepository {
    suspend fun signOut()
}