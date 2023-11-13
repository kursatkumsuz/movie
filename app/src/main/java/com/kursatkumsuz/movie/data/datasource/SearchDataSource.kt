package com.kursatkumsuz.movie.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kursatkumsuz.movie.data.model.search.SearchResult
import com.kursatkumsuz.movie.data.remote.MovieApiService

class SearchDataSource(
    private val movieApiService: MovieApiService,
    private val searchString: String
) : PagingSource<Int, SearchResult>() {
    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        val page = params.key ?: 1
        val prevKey = if (page == 1) null else page - 1
        val nextKey = page + 1
        return try {
            val response = movieApiService.searchMovie(page = page, searchString = searchString)
            LoadResult.Page(
                data = response.results,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}