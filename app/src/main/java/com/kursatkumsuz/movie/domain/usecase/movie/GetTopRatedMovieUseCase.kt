package com.kursatkumsuz.movie.domain.usecase.movie

import androidx.paging.PagingData
import com.kursatkumsuz.movie.data.model.Result
import com.kursatkumsuz.movie.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTopRatedMovieUseCase(
    val movieRepository: MovieRepository
) {

    suspend operator fun invoke(): Flow<PagingData<Result>> {
        return movieRepository.getTopRatedMovies()
    }
}