package com.kursatkumsuz.onboarding.util

import androidx.annotation.DrawableRes
import com.kursatkumsuz.onboarding.R

sealed class OnBoardingPage(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
) {
    object First : OnBoardingPage(
        title = "Choose & Watch",
        description = "Choose the movies you like and start watching now!",
        image = R.drawable.onboarding_image_first
    )

    object Second : OnBoardingPage(
        title = "Create Watchlist",
        description = "Add your favorite movies to watch list!",
        image = R.drawable.onboarding_image_second
    )

    object Third : OnBoardingPage(
        title = "Let's Start",
        description = "if you are ready let's start",
        image = R.drawable.onboarding_image_third
    )
}