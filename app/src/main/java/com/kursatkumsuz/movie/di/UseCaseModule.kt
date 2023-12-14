package com.kursatkumsuz.movie.di

import com.kursatkumsuz.detail.domain.repository.DetailMovieRepository
import com.kursatkumsuz.detail.domain.repository.FirebaseRepository
import com.kursatkumsuz.domain.repository.DataStoreRepository
import com.kursatkumsuz.domain.repository.UserRepository
import com.kursatkumsuz.domain.usecase.GetUserProfileImageUseCase
import com.kursatkumsuz.domain.usecase.GetUserUseCase
import com.kursatkumsuz.domain.usecase.UseCases
import com.kursatkumsuz.home.domain.repository.MovieRepository
import com.kursatkumsuz.home.domain.usecase.GetNowPlayingMovieUseCase
import com.kursatkumsuz.home.domain.usecase.GetPopularMovieUseCase
import com.kursatkumsuz.home.domain.usecase.GetTopRatedMovieUseCase
import com.kursatkumsuz.home.domain.usecase.HomeUseCases
import com.kursatkumsuz.profile.domain.repository.ImageRepository
import com.kursatkumsuz.profile.domain.repository.ProfileAuthenticationRepository
import com.kursatkumsuz.signup.domain.repository.AuthenticationRepository
import com.kursatkumsuz.signup.domain.repository.FirebaseStorageRepository
import com.kursatkumsuz.signup.domain.usecase.SignUpUseCases
import com.kursatkumsuz.signup.domain.usecase.authentication.SignUpWithEmailAndPasswordUseCase
import com.kursatkumsuz.signup.domain.usecase.user.SaveUserUseCase
import com.kursatkumsuz.splash.domain.repository.SplashAuthRepository
import com.kursatkumsuz.splash.domain.usecase.IsSignedInUseCase
import com.kursatkumsuz.splash.domain.usecase.ReadOnBoardingStateUseCase
import com.kursatkumsuz.splash.domain.usecase.SplashUseCases
import com.kursatkumsuz.watchlist.domain.repository.WatchListRepository
import com.kursatkumsuz.watchlist.domain.usecase.DeleteWatchListUseCase
import com.kursatkumsuz.watchlist.domain.usecase.GetWatchListUseCase
import com.kursatkumsuz.watchlist.domain.usecase.WatchListUseCases
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
    fun provideSignUpUseCases(
        authenticationRepository: AuthenticationRepository,
        firebaseStorageRepository: FirebaseStorageRepository
    ): SignUpUseCases {
        return SignUpUseCases(
            signUpWithEmailAndPasswordUseCase = SignUpWithEmailAndPasswordUseCase(
                authRepository = authenticationRepository
            ),
            saveUserUseCase = SaveUserUseCase(
                firebaseStorageRepository = firebaseStorageRepository,
                authenticationRepository = authenticationRepository
            ),
        )
    }

    @Provides
    @Singleton
    fun provideSplashUseCases(
        dataStoreRepository: DataStoreRepository,
        splashAuthRepository: SplashAuthRepository
    ): SplashUseCases {
        return SplashUseCases(
            isSignedInUseCase = IsSignedInUseCase(
                splashAuthRepository = splashAuthRepository
            ),
            readOnBoardingStateUseCase = ReadOnBoardingStateUseCase(
                dataStoreRepository = dataStoreRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideHomeUseCases(
        movieRepository: MovieRepository,
    ): HomeUseCases {
        return HomeUseCases(
            getNowPlayingMovieUseCase = GetNowPlayingMovieUseCase(
                movieRepository = movieRepository
            ),
            getPopularMovieUseCase = GetPopularMovieUseCase(
                movieRepository = movieRepository
            ),
            getTopRatedMovieUseCase = GetTopRatedMovieUseCase(
                movieRepository = movieRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(
        detailMovieRepository: DetailMovieRepository,
        firebaseRepository: FirebaseRepository
    ): com.kursatkumsuz.detail.domain.usecase.DetailUseCases {
        return com.kursatkumsuz.detail.domain.usecase.DetailUseCases(
            getCastUseCase = com.kursatkumsuz.detail.domain.usecase.GetCastUseCase(detailMovieRepository = detailMovieRepository),
            getMovieDetailUseCase = com.kursatkumsuz.detail.domain.usecase.GetMovieDetailUseCase(
                detailMovieRepository = detailMovieRepository
            ),
            saveMovieToWatchlistUseCase = com.kursatkumsuz.detail.domain.usecase.SaveMovieToWatchlistUseCase(
                firebaseRepository = firebaseRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(
        imageRepository: ImageRepository,
        profileAuthenticationRepository: ProfileAuthenticationRepository
    ): com.kursatkumsuz.profile.domain.usecase.ProfileUseCases {
        return com.kursatkumsuz.profile.domain.usecase.ProfileUseCases(
            uploadProfileImageUseCase = com.kursatkumsuz.profile.domain.usecase.UploadProfileImageUseCase(
                imageRepository = imageRepository
            ),
            saveUserProfileImageUseCase = com.kursatkumsuz.profile.domain.usecase.SaveUserProfileImageUseCase(
                imageRepository = imageRepository
            ),
            signOutUseCase = com.kursatkumsuz.profile.domain.usecase.SignOutUseCase(
                authenticationRepository = profileAuthenticationRepository
            )
        )
    }

    @Singleton
    @Provides
    fun provideWatchListUseCases(watchListRepository: WatchListRepository): WatchListUseCases {
        return WatchListUseCases(
            deleteWatchListUseCase = DeleteWatchListUseCase(
                watchListRepository = watchListRepository
            ),
            getWatchListUseCase = GetWatchListUseCase(
                watchListRepository = watchListRepository
            )
        )
    }

    @Provides
    @Singleton
    fun provideUseCase(
        userRepository: UserRepository,
    ): UseCases {
        return UseCases(
            getUserUseCase = GetUserUseCase(userRepository = userRepository),
            getUserProfileImageUseCase = GetUserProfileImageUseCase(userRepository = userRepository),
        )
    }
}


