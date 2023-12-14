package com.kursatkumsuz.detail.domain.repository

import com.google.android.gms.tasks.Task
import com.kursatkumsuz.domain.model.WatchListMovie

interface FirebaseRepository {
    suspend fun getUserUid(): String
    suspend fun saveMovie(movie: WatchListMovie): Task<Void?>

}