package com.kursatkumsuz.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kursatkumsuz.search.data.datasource.SearchDataSource
import com.kursatkumsuz.search.data.model.SearchResult
import com.kursatkumsuz.search.data.remote.SearchApiService
import com.kursatkumsuz.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val searchApiService: SearchApiService
) : SearchRepository {

    override suspend fun searchMovie(searchString: String): Flow<PagingData<SearchResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6
            ),
            pagingSourceFactory = {
                SearchDataSource(
                    searchApiService = searchApiService,
                    searchString = searchString
                )
            }
        ).flow
    }
}