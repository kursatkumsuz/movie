package com.kursatkumsuz.movie.domain.usecase.movie

import androidx.paging.PagingData
import com.kursatkumsuz.movie.data.model.search.SearchResult
import com.kursatkumsuz.movie.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class SearchMovieUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(searchString: String): Flow<PagingData<SearchResult>> {
        return movieRepository.searchMovie(searchString)
    }
}