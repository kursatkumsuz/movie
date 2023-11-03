package com.kursatkumsuz.movie.di

import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository
import com.kursatkumsuz.movie.domain.repository.DataStoreRepository
import com.kursatkumsuz.movie.domain.repository.movie.MovieRepository
import com.kursatkumsuz.movie.domain.usecase.UseCases
import com.kursatkumsuz.movie.domain.usecase.authentication.SignInWithEmailAndPasswordUseCase
import com.kursatkumsuz.movie.domain.usecase.authentication.SignUpWithEmailAndPasswordUseCase
import com.kursatkumsuz.movie.domain.usecase.authentication.IsSignedInUseCase
import com.kursatkumsuz.movie.domain.usecase.datastore.ReadOnBoardingStateUseCase
import com.kursatkumsuz.movie.domain.usecase.datastore.SaveOnBoardingStateUseCase
import com.kursatkumsuz.movie.domain.usecase.movie.GetNowPlayingMovieUseCase
import com.kursatkumsuz.movie.domain.usecase.movie.GetPopularMovieUseCase
import com.kursatkumsuz.movie.domain.usecase.movie.GetTopRatedMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideUseCases(
        dataStoreRepository: DataStoreRepository,
        authenticationRepository: AuthenticationRepository,
        movieRepository: MovieRepository
    ): UseCases {
        return UseCases(
            saveOnBoardingStateUseCase = SaveOnBoardingStateUseCase(dataStoreRepository),
            readOnBoardingStateUseCase = ReadOnBoardingStateUseCase(dataStoreRepository),
            signInWithEmailAndPasswordUseCase = SignInWithEmailAndPasswordUseCase(authRepository = authenticationRepository),
            signUpWithEmailAndPasswordUseCase = SignUpWithEmailAndPasswordUseCase(authRepository = authenticationRepository),
            isSignedInUseCase = IsSignedInUseCase(authRepository = authenticationRepository),
            getTopRatedMovieUseCase = GetTopRatedMovieUseCase(movieRepository = movieRepository),
            getPopularMovieUseCase = GetPopularMovieUseCase(movieRepository = movieRepository),
            getNowPlayingMovieUseCase = GetNowPlayingMovieUseCase(movieRepository)
        )
    }
}