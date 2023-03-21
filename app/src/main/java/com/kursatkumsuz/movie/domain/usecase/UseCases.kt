package com.kursatkumsuz.movie.domain.usecase

import com.kursatkumsuz.movie.domain.usecase.datastore.ReadOnBoardingStateUseCase
import com.kursatkumsuz.movie.domain.usecase.datastore.SaveOnBoardingStateUseCase

data class UseCases(
    val saveOnBoardingStateUseCase: SaveOnBoardingStateUseCase,
    val readOnBoardingStateUseCase: ReadOnBoardingStateUseCase
)
