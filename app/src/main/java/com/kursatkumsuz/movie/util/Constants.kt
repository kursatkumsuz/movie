package com.kursatkumsuz.movie.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.kursatkumsuz.movie.BuildConfig

object Constants {

    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL =  "https://image.tmdb.org/t/p"
    const val LANGUAGE = "en-US"
    const val DATASTORE_PREFERENCES_NAME =  "movie_preferences"
    const val MOVIE_ID =  "movieID"
    const val WATCHLIST_COLLECTION =  "watchlist"
    val DATASTORE_PREFERENCES_KEY =  booleanPreferencesKey("onboarding_completed")

}