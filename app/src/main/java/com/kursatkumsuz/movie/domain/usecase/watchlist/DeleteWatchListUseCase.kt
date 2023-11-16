package com.kursatkumsuz.movie.domain.usecase.watchlist

import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository
import com.kursatkumsuz.movie.domain.repository.FirebaseStorageRepository
import kotlinx.coroutines.tasks.await

class DeleteWatchListUseCase(
    val firebaseStorageRepository: FirebaseStorageRepository,
    val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(movieId: String) {
        val userUid = authenticationRepository.getUserUid()
        try {
            firebaseStorageRepository.deleteMovie(userUid, movieId).await()
        } catch (e: Exception) {
            println(e.localizedMessage ?: "Watchlist could not be deleted!")
        }
    }
}