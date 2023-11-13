package com.kursatkumsuz.movie.data.model.search

import com.google.gson.annotations.SerializedName

data class Search(
    val page: Int,
    val results: List<SearchResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)