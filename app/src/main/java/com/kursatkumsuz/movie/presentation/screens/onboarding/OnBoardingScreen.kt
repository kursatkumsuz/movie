package com.kursatkumsuz.movie.presentation.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.kursatkumsuz.movie.util.OnBoardingPage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    pagerState: PagerState,
    onStartClick: () -> Unit
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            count = pages.size,
            verticalAlignment = Alignment.Top
        ) { position ->
            OnBoardingContent(onBoardingPage = pages[position])
        }
        Spacer(modifier = Modifier.height(50.dp))
        HorizontalPagerIndicator(
            pagerState = pagerState,
            inactiveColor = Color.LightGray,
            activeColor = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        StartButton( pagerState = pagerState, onStartClick = onStartClick)
    }
}



