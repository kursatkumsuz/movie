package com.kursatkumsuz.util

import androidx.datastore.preferences.core.booleanPreferencesKey


object Constants {
     const val API_KEY = BuildConfig.API_KEY
     const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"
     const val EMPTY_IMAGE = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"
     const val DATASTORE_PREFERENCES_NAME = "movie_preferences"
     val DATASTORE_PREFERENCES_KEY = booleanPreferencesKey("onboarding_completed")
     const val USER_COLLECTION = "Users"
     const val IMAGE_COLLECTION = "ProfilePhotos"
     const val MOVIE_ID = "movieID"
     const val LANGUAGE = "en-US"
     const val WATCHLIST_COLLECTION = "watchlist"


}