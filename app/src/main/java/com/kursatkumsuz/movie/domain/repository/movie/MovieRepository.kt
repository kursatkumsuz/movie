package com.kursatkumsuz.movie.domain.repository.movie

import androidx.paging.PagingData
import com.kursatkumsuz.movie.data.model.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTopRatedMovies() : Flow<PagingData<Result>>

    suspend fun getPopularMovies() : Flow<PagingData<Result>>

    suspend fun getNowPlayingMovies() : Flow<PagingData<Result>>

}