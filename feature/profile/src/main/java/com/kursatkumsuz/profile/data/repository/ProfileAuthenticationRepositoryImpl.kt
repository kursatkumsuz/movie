package com.kursatkumsuz.profile.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.kursatkumsuz.profile.domain.repository.ProfileAuthenticationRepository

class ProfileAuthenticationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : ProfileAuthenticationRepository {

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

}