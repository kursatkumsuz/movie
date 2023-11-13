package com.kursatkumsuz.movie.data.model.credit

data class Credit(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)