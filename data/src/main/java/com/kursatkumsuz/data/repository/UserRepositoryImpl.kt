package com.kursatkumsuz.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.kursatkumsuz.data.util.Constants.USER_COLLECTION
import com.kursatkumsuz.domain.repository.UserRepository
import com.kursatkumsuz.util.Constants.IMAGE_COLLECTION

class UserRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {
    override suspend fun getUserUid(): String {
        var uid = ""
        firebaseAuth.currentUser?.uid?.let {
            uid = it
        }
        return uid
    }


    override suspend fun getUser(): Task<QuerySnapshot?> {
        return firebaseFirestore.collection(USER_COLLECTION).document(getUserUid()).collection("info")
            .get()
    }

    override suspend fun getProfileImage(): Task<DocumentSnapshot?> {
        return firebaseFirestore.collection(IMAGE_COLLECTION).document(getUserUid()).get()
    }
}