package com.kursatkumsuz.movie.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kursatkumsuz.movie.data.MovieDataSource
import com.kursatkumsuz.movie.data.model.Result
import com.kursatkumsuz.movie.data.remote.MovieApiService
import com.kursatkumsuz.movie.domain.repository.movie.MovieRepository
import com.kursatkumsuz.movie.util.MovieType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
) : MovieRepository {
    override suspend fun getTopRatedMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3,
            ),
            pagingSourceFactory = {
                MovieDataSource(
                    movieApiService = movieApiService,
                    movieType = MovieType.TOP_RATED
                )
            },

            ).flow
    }

    override suspend fun getPopularMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3,
            ),
            pagingSourceFactory = {
                MovieDataSource(
                    movieApiService = movieApiService,
                    movieType = MovieType.POPULAR
                )
            },
        ).flow
    }

    override suspend fun getNowPlayingMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1
            ),
            pagingSourceFactory = {
                MovieDataSource(
                    movieApiService = movieApiService,
                    movieType = MovieType.NOW_PLAYING
                )
            }
        ).flow
    }
}