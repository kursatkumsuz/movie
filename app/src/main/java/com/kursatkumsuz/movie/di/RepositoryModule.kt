package com.kursatkumsuz.movie.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.kursatkumsuz.movie.data.repository.AuthenticationRepositoryImpl
import com.kursatkumsuz.movie.data.repository.DataStoreRepositoryImpl
import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository
import com.kursatkumsuz.movie.domain.repository.DataStoreRepository
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
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(firebaseAuth: FirebaseAuth): AuthenticationRepository {
        return AuthenticationRepositoryImpl(firebaseAuth = firebaseAuth)
    }

}