package com.kursatkumsuz.movie.presentation.screens.detail

import com.kursatkumsuz.movie.domain.model.movie.detail.MovieDetailUI

data class DetailUIState(
    var movie : MovieDetailUI? = null,
    var isLoading : Boolean = false,
    var error : String = ""
)
