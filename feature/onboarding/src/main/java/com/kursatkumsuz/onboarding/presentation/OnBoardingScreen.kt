package com.kursatkumsuz.onboarding.presentation

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.kursatkumsuz.onboarding.util.OnBoardingPage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    onNavigateAuthenticationScreen : () -> Unit
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val viewModel: OnBoardingViewModel = hiltViewModel()
    val pagerState = rememberPagerState()
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
            val pageOffset = (pagerState.currentPage - position) + pagerState.currentPageOffset

            OnBoardingContent(
                onBoardingPage = pages[position],
                pageOffset = pageOffset
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        HorizontalPagerIndicator(
            pagerState = pagerState,
            inactiveColor = Color.LightGray,
            activeColor = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        StartButton(pagerState = pagerState, onStartClick = {
            viewModel.saveOnBoardingState()
            onNavigateAuthenticationScreen()
        })
    }
}



