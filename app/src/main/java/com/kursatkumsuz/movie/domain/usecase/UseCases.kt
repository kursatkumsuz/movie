package com.kursatkumsuz.movie.domain.usecase

import com.kursatkumsuz.movie.domain.usecase.authentication.SignInWithEmailAndPasswordUseCase
import com.kursatkumsuz.movie.domain.usecase.authentication.SignUpWithEmailAndPasswordUseCase
import com.kursatkumsuz.movie.domain.usecase.authentication.IsSignedInUseCase
import com.kursatkumsuz.movie.domain.usecase.datastore.ReadOnBoardingStateUseCase
import com.kursatkumsuz.movie.domain.usecase.datastore.SaveOnBoardingStateUseCase
import com.kursatkumsuz.movie.domain.usecase.movie.GetNowPlayingMovieUseCase
import com.kursatkumsuz.movie.domain.usecase.movie.GetPopularMovieUseCase
import com.kursatkumsuz.movie.domain.usecase.movie.GetTopRatedMovieUseCase

data class UseCases(
    val saveOnBoardingStateUseCase: SaveOnBoardingStateUseCase,
    val readOnBoardingStateUseCase: ReadOnBoardingStateUseCase,
    val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
    val isSignedInUseCase: IsSignedInUseCase,
    val getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    val getPopularMovieUseCase: GetPopularMovieUseCase,
    val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase
)
