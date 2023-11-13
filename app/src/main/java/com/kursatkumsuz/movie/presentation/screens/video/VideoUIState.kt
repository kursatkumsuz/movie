package com.kursatkumsuz.movie.presentation.screens.video

import com.kursatkumsuz.movie.data.model.video.MovieVideo

data class VideoUIState(
    var movie: MovieVideo? = null,
    var isLoading: Boolean = false,
    var error: String = ""
)