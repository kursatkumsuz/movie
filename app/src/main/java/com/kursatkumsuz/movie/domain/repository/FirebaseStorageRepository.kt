package com.kursatkumsuz.movie.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie

interface FirebaseStorageRepository {
    suspend fun saveMovie(movie : WatchListMovie, userUid : String) : Task<Void?>

    suspend fun deleteMovie(userUid : String, movieId : String) : Task<Void?>

    suspend fun getMovies(userUid : String) : Task<QuerySnapshot?>
}