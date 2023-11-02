package com.kursatkumsuz.movie.data.mappers

import com.kursatkumsuz.movie.data.model.Movie
import com.kursatkumsuz.movie.data.model.Result
import com.kursatkumsuz.movie.domain.model.movie.MovieHomeUI
import com.kursatkumsuz.movie.domain.model.movie.ResultHomeUI


fun Movie.toMovieHomeUI(): MovieHomeUI {
    val result = results.map { ResultHomeUI(id = it.id, posterPath = it.posterPath, title = it.title) }
    return MovieHomeUI(
        page = page,
        result = result
    )
}

fun Result.toResultHomeUI() : ResultHomeUI {
    return ResultHomeUI(
        id = id,
        posterPath = posterPath,
        title = title
    )
}




