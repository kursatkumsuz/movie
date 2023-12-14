package com.kursatkumsuz.movie.di

import android.content.Context
import com.kursatkumsuz.home.data.remote.ApiService
import com.kursatkumsuz.detail.data.remote.DetailApiService
import com.kursatkumsuz.search.data.remote.SearchApiService
import com.kursatkumsuz.movie.util.Constants.BASE_URL
import com.kursatkumsuz.movie.util.interceptors.CacheInterceptor
import com.kursatkumsuz.video.data.remote.VideoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCacheInterceptor() = CacheInterceptor()

    @Singleton
    @Provides
    fun provideOkHttp(
        @ApplicationContext context: Context,
        cacheInterceptor: CacheInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L))
            .addNetworkInterceptor(cacheInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideVideoApiService(retrofit: Retrofit): VideoApiService =
        retrofit.create(VideoApiService::class.java)

    @Singleton
    @Provides
    fun provideDetailApiService(retrofit: Retrofit): DetailApiService =
        retrofit.create(DetailApiService::class.java)

    @Singleton
    @Provides
    fun provideSearchApiService(retrofit: Retrofit): SearchApiService =
        retrofit.create(SearchApiService::class.java)
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}