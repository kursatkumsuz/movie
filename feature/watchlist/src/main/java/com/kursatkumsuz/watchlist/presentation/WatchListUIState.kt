package com.kursatkumsuz.watchlist.presentation

import com.kursatkumsuz.domain.model.WatchListMovie

data class WatchListUIState (
    val movie : List<WatchListMovie> = listOf(),
    var loading : Boolean = false,
    var error : String = ""
)
