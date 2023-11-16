package com.kursatkumsuz.movie.domain.usecase.movie

import com.kursatkumsuz.movie.domain.repository.movie.MovieRepository
import com.kursatkumsuz.movie.util.Response
import kotlinx.coroutines.flow.flow

class GetMovieVideoUseCase(
    val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int) = flow {
        try {
            emit(Response.Loading)
            val result = movieRepository.getMovieVideo(movieId = movieId)
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}