package com.kursatkumsuz.detail.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kursatkumsuz.domain.model.WatchListMovie
import com.kursatkumsuz.detail.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl (
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
): FirebaseRepository {
    override suspend fun getUserUid(): String {
        var uid = ""
        firebaseAuth.currentUser?.uid?.let {
            uid = it
        }
        return uid
    }

    override suspend fun saveMovie(movie: WatchListMovie): Task<Void?> {
        return firebaseFirestore
            .collection(com.kursatkumsuz.util.Constants.WATCHLIST_COLLECTION)
            .document(getUserUid())
            .collection("movies")
            .document(movie.movieId.toString())
            .set(movie)
    }
}