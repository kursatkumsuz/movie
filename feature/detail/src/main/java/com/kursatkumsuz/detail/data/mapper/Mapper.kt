package com.kursatkumsuz.detail.data.mapper

import com.kursatkumsuz.detail.data.model.credit.Cast
import com.kursatkumsuz.detail.data.model.detail.MovieDetail
import com.kursatkumsuz.detail.domain.model.CastUI
import com.kursatkumsuz.detail.domain.model.MovieDetailUI


fun MovieDetail.toMovieDetailUI(): MovieDetailUI {
    return MovieDetailUI(
        backDropPath = backDropPath,
        genres = genres,
        id = id,
        originalLanguage = originalLanguage,
        overview = overview,
        releaseDate = releaseDate,
        title = title,
        status = status
    )
}

fun Cast.toCastUI(): CastUI {
    return CastUI(
        name = originalName,
        character = character,
        profilePath = profilePath
    )
}