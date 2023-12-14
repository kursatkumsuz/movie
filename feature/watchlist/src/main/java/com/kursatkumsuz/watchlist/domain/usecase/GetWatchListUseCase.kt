package com.kursatkumsuz.watchlist.domain.usecase

import com.kursatkumsuz.watchlist.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class GetWatchListUseCase(
    val watchListRepository: WatchListRepository
) {
    operator fun invoke() = flow {
        try {
            emit(com.kursatkumsuz.util.Response.Loading)
            val querySnapshot = watchListRepository.getMovies().await()

            emit(com.kursatkumsuz.util.Response.Success(querySnapshot))

        } catch (e: Exception) {
            emit(com.kursatkumsuz.util.Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}
