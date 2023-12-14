package com.kursatkumsuz.watchlist.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.kursatkumsuz.watchlist.domain.repository.WatchListRepository
import com.kursatkumsuz.util.Constants

class WatchListRepositoryImpl (
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
): WatchListRepository {

    override suspend fun getUserUid(): String {
        var uid = ""
        firebaseAuth.currentUser?.uid?.let {
            uid = it
        }
        return uid
    }
    override suspend fun deleteMovie(movieId: String): Task<Void?> {
        val uid = getUserUid()
        return firebaseFirestore
            .collection(Constants.WATCHLIST_COLLECTION)
            .document(uid)
            .collection("movies")
            .document(movieId)
            .delete()
    }

    override suspend fun getMovies(): Task<QuerySnapshot?> {
        val uid = getUserUid()
        return firebaseFirestore
            .collection(Constants.WATCHLIST_COLLECTION)
            .document(uid)
            .collection("movies")
            .get()
    }
}