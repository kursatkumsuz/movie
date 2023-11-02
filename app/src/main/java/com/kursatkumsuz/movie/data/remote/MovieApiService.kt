package com.kursatkumsuz.movie.data.remote

import com.kursatkumsuz.movie.data.model.Movie
import com.kursatkumsuz.movie.util.Constants.API_KEY
import com.kursatkumsuz.movie.util.Constants.LANGUAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ) : Movie

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ) : Movie

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ) : Movie
}