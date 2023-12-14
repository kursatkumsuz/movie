package com.kursatkumsuz.search.domain.repository

import androidx.paging.PagingData
import com.kursatkumsuz.search.data.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchMovie(searchString : String) : Flow<PagingData<SearchResult>>

}