package com.kursatkumsuz.detail.presentation

import com.kursatkumsuz.detail.domain.model.MovieDetailUI

data class DetailUIState(
    var movie : MovieDetailUI? = null,
    var isLoading : Boolean = false,
    var error : String = ""
)
