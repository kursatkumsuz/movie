package com.kursatkumsuz.detail.data.repository

import com.kursatkumsuz.detail.data.model.credit.Credit
import com.kursatkumsuz.detail.data.model.detail.MovieDetail
import com.kursatkumsuz.detail.domain.repository.DetailMovieRepository
import com.kursatkumsuz.detail.data.remote.DetailApiService
class DetailMovieRepositoryImpl (
    private val detailApiService: DetailApiService
) : DetailMovieRepository {
    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return detailApiService.getMovieDetail(movieId = movieId)
    }

    override suspend fun getCredit(movieId: Int): Credit {
        return detailApiService.getCredit(movieId = movieId)
    }
}