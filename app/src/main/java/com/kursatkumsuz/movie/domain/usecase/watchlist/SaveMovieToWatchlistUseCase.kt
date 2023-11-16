package com.kursatkumsuz.movie.domain.usecase.watchlist

import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie
import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository
import com.kursatkumsuz.movie.domain.repository.FirebaseStorageRepository
import com.kursatkumsuz.movie.util.Response

class SaveMovieToWatchlistUseCase(
    val firebaseStorageRepository: FirebaseStorageRepository,
    val authRepository: AuthenticationRepository
) {
    suspend operator fun invoke(movie: WatchListMovie) {
        try {
            Response.Loading
            val userUid = authRepository.getUserUid()
            val result = firebaseStorageRepository.saveMovie(movie = movie, userUid = userUid)
            Response.Success(result)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage ?: "Could not be saved!")
        }
    }
}