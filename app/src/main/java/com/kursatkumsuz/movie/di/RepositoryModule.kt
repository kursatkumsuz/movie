package com.kursatkumsuz.movie.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kursatkumsuz.data.repository.DataStoreRepositoryImpl
import com.kursatkumsuz.data.repository.UserRepositoryImpl
import com.kursatkumsuz.domain.repository.DataStoreRepository
import com.kursatkumsuz.domain.repository.UserRepository
import com.kursatkumsuz.signup.data.repository.AuthenticationRepositoryImpl
import com.kursatkumsuz.signup.data.repository.FirebaseStorageRepositoryImpl
import com.kursatkumsuz.splash.data.repository.SplashAuthRepositoryImpl
import com.kursatkumsuz.watchlist.data.repository.WatchListRepositoryImpl
import com.kursatkumsuz.profile.data.repository.ImageRepositoryImpl
import com.kursatkumsuz.profile.data.repository.ProfileAuthenticationRepositoryImpl
import com.kursatkumsuz.profile.domain.repository.ImageRepository
import com.kursatkumsuz.signup.domain.repository.FirebaseStorageRepository
import com.kursatkumsuz.video.data.remote.VideoApiService
import com.kursatkumsuz.video.data.repository.VideoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideSignInAuthenticationRepository(firebaseAuth: FirebaseAuth): com.kursatkumsuz.signin.domain.repository.AuthenticationRepository {
        return com.kursatkumsuz.signin.data.repository.AuthenticationRepositoryImpl(
            firebaseAuth = firebaseAuth
        )
    }

    @Provides
    @Singleton
    fun provideSplashAuthRepository(firebaseAuth: FirebaseAuth): com.kursatkumsuz.splash.domain.repository.SplashAuthRepository {
        return SplashAuthRepositoryImpl(firebaseAuth = firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(firebaseAuth: FirebaseAuth): com.kursatkumsuz.signup.domain.repository.AuthenticationRepository {
        return AuthenticationRepositoryImpl(firebaseAuth = firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideProfileAuthenticationRepository(firebaseAuth: FirebaseAuth): com.kursatkumsuz.profile.domain.repository.ProfileAuthenticationRepository {
        return ProfileAuthenticationRepositoryImpl(firebaseAuth = firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideImageRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
        firebaseStorage: FirebaseStorage
    ): ImageRepository {
        return ImageRepositoryImpl(
            firebaseAuth = firebaseAuth,
            firebaseFirestore = firebaseFirestore,
            firebaseStorage = firebaseStorage
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: com.kursatkumsuz.home.data.remote.ApiService): com.kursatkumsuz.home.domain.repository.MovieRepository {
        return com.kursatkumsuz.home.data.repository.MovieRepositoryImpl(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideWatchListRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): com.kursatkumsuz.watchlist.domain.repository.WatchListRepository {
        return WatchListRepositoryImpl(
            firebaseFirestore = firebaseFirestore,
            firebaseAuth = firebaseAuth
        )
    }

    @Provides
    @Singleton
    fun provideVideoRepository(videoApiService: VideoApiService): com.kursatkumsuz.video.domain.repository.VideoRepository {
        return VideoRepositoryImpl(videoApiService = videoApiService)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(searchApiService: com.kursatkumsuz.search.data.remote.SearchApiService): com.kursatkumsuz.search.domain.repository.SearchRepository {
        return com.kursatkumsuz.search.data.repository.SearchRepositoryImpl(searchApiService = searchApiService)
    }

    @Provides
    @Singleton
    fun provideFirebaseStorageRepository(
        firebaseFirestore: FirebaseFirestore,
    ): FirebaseStorageRepository {
        return FirebaseStorageRepositoryImpl(
            firebaseFirestore = firebaseFirestore,
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): UserRepository {
        return UserRepositoryImpl(
            firebaseFirestore = firebaseFirestore,
            firebaseAuth = firebaseAuth
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): com.kursatkumsuz.detail.domain.repository.FirebaseRepository {
        return com.kursatkumsuz.detail.data.repository.FirebaseRepositoryImpl(
            firebaseFirestore = firebaseFirestore,
            firebaseAuth = firebaseAuth
        )
    }

    @Provides
    @Singleton
    fun provideDetailMovieRepository(detailApiService: com.kursatkumsuz.detail.data.remote.DetailApiService): com.kursatkumsuz.detail.domain.repository.DetailMovieRepository {
        return com.kursatkumsuz.detail.data.repository.DetailMovieRepositoryImpl(detailApiService = detailApiService)
    }

}