package com.kursatkumsuz.signup.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.kursatkumsuz.domain.model.User

interface FirebaseStorageRepository {

    suspend fun saveUser(
        userUid: String,
        user: User
    ): Task<DocumentReference?>

}