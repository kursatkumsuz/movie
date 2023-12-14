package com.kursatkumsuz.detail.data.remote

import com.kursatkumsuz.detail.data.model.credit.Credit
import com.kursatkumsuz.detail.data.model.detail.MovieDetail
import com.kursatkumsuz.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailApiService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
    ): MovieDetail

    @GET("movie/{movie_id}/credits")
    suspend fun getCredit(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
    ): Credit
}