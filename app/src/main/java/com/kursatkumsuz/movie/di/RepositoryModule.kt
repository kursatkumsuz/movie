package com.kursatkumsuz.movie.di

import android.content.Context
import com.kursatkumsuz.movie.data.repository.DataStoreRepositoryImpl
import com.kursatkumsuz.movie.domain.repository.DataStoreRepository
import com.kursatkumsuz.movie.domain.usecase.UseCases
import com.kursatkumsuz.movie.domain.usecase.datastore.ReadOnBoardingStateUseCase
import com.kursatkumsuz.movie.domain.usecase.datastore.SaveOnBoardingStateUseCase
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

    @Singleton
    @Provides
    fun provideUseCases(dataStoreRepository: DataStoreRepository): UseCases {
        return UseCases(
            saveOnBoardingStateUseCase = SaveOnBoardingStateUseCase(dataStoreRepository),
            readOnBoardingStateUseCase = ReadOnBoardingStateUseCase(dataStoreRepository)
        )
    }
}