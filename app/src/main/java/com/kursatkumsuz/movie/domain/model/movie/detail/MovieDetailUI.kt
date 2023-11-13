package com.kursatkumsuz.movie.domain.model.movie.detail

import com.kursatkumsuz.movie.data.model.detail.Genre


data class MovieDetailUI (
    val backDropPath: String,
    val genres: List<Genre>,
    val status : String,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: String,
    val title: String,
)

