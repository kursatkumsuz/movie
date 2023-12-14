package com.kursatkumsuz.search.data.remote

import com.kursatkumsuz.search.data.model.Search
import com.kursatkumsuz.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int = 1,
        @Query("query") searchString: String = "",
        @Query("include_adult") adult: Boolean = true,
    ): Search
}