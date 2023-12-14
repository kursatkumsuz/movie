package com.kursatkumsuz.detail.domain.usecase

import com.kursatkumsuz.detail.domain.repository.FirebaseRepository
import com.kursatkumsuz.domain.model.WatchListMovie
import kotlinx.coroutines.flow.flow

class SaveMovieToWatchlistUseCase(
    val firebaseRepository: FirebaseRepository,
) {
    operator fun invoke(movie: WatchListMovie) = flow {
        try {
            emit(com.kursatkumsuz.util.Response.Loading)
            val result = firebaseRepository.saveMovie(movie = movie)
            emit(com.kursatkumsuz.util.Response.Success(result))
        } catch (e: Exception) {
            emit(com.kursatkumsuz.util.Response.Error(e.localizedMessage ?: "Could not be saved!"))
        }
    }
}