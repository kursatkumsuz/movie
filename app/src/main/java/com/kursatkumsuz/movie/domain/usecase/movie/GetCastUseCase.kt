package com.kursatkumsuz.movie.domain.usecase.movie

import com.kursatkumsuz.movie.data.mappers.toCastUI
import com.kursatkumsuz.movie.domain.repository.movie.MovieRepository
import com.kursatkumsuz.movie.util.Response
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCastUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int) = flow {
        try {
            emit(Response.Loading)
            val result = movieRepository.getCredit(movieId = movieId).cast.map { it -> it.toCastUI() }
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}