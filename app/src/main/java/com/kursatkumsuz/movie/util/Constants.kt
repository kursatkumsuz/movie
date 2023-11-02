package com.kursatkumsuz.movie.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.kursatkumsuz.movie.BuildConfig

object Constants {

    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val LANGUAGE = "en-US"
    const val DATASTORE_PREFERENCES_NAME =  "movie_preferences"
    val DATASTORE_PREFERENCES_KEY =  booleanPreferencesKey("onboarding_completed")

}