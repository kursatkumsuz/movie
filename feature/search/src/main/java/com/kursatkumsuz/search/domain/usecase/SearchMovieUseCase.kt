package com.kursatkumsuz.search.domain.usecase

import androidx.paging.PagingData
import com.kursatkumsuz.search.data.model.SearchResult
import com.kursatkumsuz.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(searchString: String): Flow<PagingData<SearchResult>> {
        return searchRepository.searchMovie(searchString)
    }
}