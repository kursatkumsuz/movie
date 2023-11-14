package com.kursatkumsuz.movie.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie
import com.kursatkumsuz.movie.domain.repository.FirebaseStorageRepository
import com.kursatkumsuz.movie.util.Constants.WATCHLIST_COLLECTION

class FirebaseStorageRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseStorageRepository {

    override suspend fun saveMovie(movie: WatchListMovie, userUid: String): Task<Void?> {
        return firebaseFirestore
            .collection(WATCHLIST_COLLECTION)
            .document(userUid)
            .collection("movies")
            .document(movie.movieId.toString())
            .set(movie)
    }

    override suspend fun deleteMovie(userUid: String, movieId: String): Task<Void?> {
        return firebaseFirestore
            .collection(WATCHLIST_COLLECTION)
            .document(userUid)
            .collection("movies")
            .document(movieId)
            .delete()
    }

    override suspend fun getMovies(userUid: String): Task<QuerySnapshot?> {
        return firebaseFirestore
            .collection(WATCHLIST_COLLECTION)
            .document(userUid)
            .collection("movies")
            .get()
    }

}