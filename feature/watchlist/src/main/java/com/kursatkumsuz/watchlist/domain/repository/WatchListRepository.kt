package com.kursatkumsuz.watchlist.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface WatchListRepository {
    suspend fun getUserUid(): String

    suspend fun deleteMovie(movieId: String): Task<Void?>

    suspend fun getMovies(): Task<QuerySnapshot?>
}