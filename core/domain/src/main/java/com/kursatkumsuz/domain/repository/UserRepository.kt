package com.kursatkumsuz.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface UserRepository {
    suspend fun getUserUid(): String
    suspend fun getUser(): Task<QuerySnapshot?>
    suspend fun getProfileImage() : Task<DocumentSnapshot?>

}