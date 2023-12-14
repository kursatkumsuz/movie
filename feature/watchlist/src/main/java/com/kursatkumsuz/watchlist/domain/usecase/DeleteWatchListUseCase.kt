package com.kursatkumsuz.watchlist.domain.usecase

import com.kursatkumsuz.util.Response
import com.kursatkumsuz.watchlist.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class DeleteWatchListUseCase(
    private val watchListRepository: WatchListRepository
) {
    suspend operator fun invoke(movieId: String) = flow {
        try {
            emit(Response.Loading)
            val result = watchListRepository.deleteMovie(movieId).await()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Movie could not be deleted!"))
        }
    }
}