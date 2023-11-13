package com.kursatkumsuz.movie.domain.usecase.movie

import com.kursatkumsuz.movie.data.mappers.toMovieDetailUI
import com.kursatkumsuz.movie.domain.repository.movie.MovieRepository
import com.kursatkumsuz.movie.util.Response
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int) = flow {
        return@flow try {
            emit(Response.Loading)
            val result = movieRepository.getMovieDetail(movieId = movieId).toMovieDetailUI()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}