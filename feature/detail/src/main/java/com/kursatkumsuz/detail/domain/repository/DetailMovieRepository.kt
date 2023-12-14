package com.kursatkumsuz.detail.domain.repository

import com.kursatkumsuz.detail.data.model.credit.Credit
import com.kursatkumsuz.detail.data.model.detail.MovieDetail

interface DetailMovieRepository {

    suspend fun getCredit(movieId : Int) : Credit

    suspend fun getMovieDetail(movieId : Int) : MovieDetail

}