package com.kursatkumsuz.movie.presentation.screens.watchlist

import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie

data class WatchListUIState (
    val movie : List<WatchListMovie> = listOf(),
    var loading : Boolean = false,
    var error : String = ""
)
