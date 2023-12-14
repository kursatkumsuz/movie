package com.kursatkumsuz.home.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kursatkumsuz.home.data.model.Result
import com.kursatkumsuz.home.data.remote.ApiService
import com.kursatkumsuz.home.util.MovieType

class MovieDataSource(
    private val apiService: ApiService,
    private val movieType: MovieType
) : PagingSource<Int, Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        val prevKey = if (page == 1) null else page - 1
        val nextKey = page + 1
        return try {
            val response = when (movieType) {
                MovieType.TOP_RATED -> {
                    apiService.getTopRatedMovie(page = page)
                }
                MovieType.POPULAR -> {
                    apiService.getPopularMovie(page = page)
                }
                MovieType.NOW_PLAYING -> {
                    apiService.getNowPlayingMovie(page = page)
                }
                MovieType.UPCOMING -> {
                    apiService.getPopularMovie(page = page)
                }
            }
            LoadResult.Page(
                data = response.results,
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }
}