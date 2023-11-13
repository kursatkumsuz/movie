package com.kursatkumsuz.movie.domain.usecase.watchlist

import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository
import com.kursatkumsuz.movie.domain.repository.FirebaseStorageRepository
import com.kursatkumsuz.movie.util.Response
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetWatchListUseCase @Inject constructor(
    private val firebaseStorageRepository: FirebaseStorageRepository,
    private val authRepository: AuthenticationRepository
) {
    operator fun invoke() = flow {
        try {
            emit(Response.Loading)
            val userUid = authRepository.getUserUid()
            val querySnapshot = firebaseStorageRepository.getMovies(userUid).await()

            emit(Response.Success(querySnapshot))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}
