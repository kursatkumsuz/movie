package com.kursatkumsuz.movie.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kursatkumsuz.movie.data.remote.MovieApiService
import com.kursatkumsuz.movie.data.model.Result
import com.kursatkumsuz.movie.util.MovieType

class MovieDataSource(
    private val movieApiService: MovieApiService,
    private val movieType: MovieType
) : PagingSource<Int, Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        val prevKey = if (page == 1) null else page - 1
        val nextKey = page + 1
        return try {
            val response = when (movieType) {
                MovieType.TOP_RATED -> {
                    movieApiService.getTopRatedMovie(page = page)
                }
                MovieType.POPULAR -> {
                    movieApiService.getPopularMovie(page = page)
                }
                MovieType.NOW_PLAYING -> {
                    movieApiService.getNowPlayingMovie(page = page)
                }
                MovieType.UPCOMING -> {
                    movieApiService.getPopularMovie(page = page)
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