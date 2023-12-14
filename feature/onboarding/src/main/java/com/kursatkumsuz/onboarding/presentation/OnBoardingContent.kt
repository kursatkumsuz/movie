package com.kursatkumsuz.onboarding.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.kursatkumsuz.onboarding.util.OnBoardingPage

@Composable
fun OnBoardingContent(onBoardingPage: OnBoardingPage, pageOffset: Float) {
    val screenWidth = LocalConfiguration.current.screenWidthDp / 2
    val contentHeight = (LocalConfiguration.current.screenHeightDp / 3 * 2.1).dp
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0x0),
            Color(0x1A000000),
            Color(0x4D000000),
            Color(0xB3000000),
            Color(0xFF000000)
        )
    )
    Card {
        Column(
            modifier = Modifier
                .height(contentHeight)
                .background(Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset {
                            IntOffset(
                                x = (screenWidth.dp * pageOffset).roundToPx(),
                                y = 0,
                            )
                        },
                    painter = painterResource(id = onBoardingPage.image),
                    contentDescription = "OnBoarding Image",
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradient)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = onBoardingPage.title,
                        color = Color.White,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.width(250.dp),
                        text = onBoardingPage.description,
                        color = Color.LightGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun StartButton(pagerState: PagerState, onStartClick: () -> Unit) {
    AnimatedVisibility(
        visible = pagerState.currentPage == 2,
        enter = expandVertically(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier.size(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4761B5)
                ),
                onClick = onStartClick,
                contentPadding = PaddingValues(5.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Start Button Icon",
                    tint = Color.White
                )
            }
        }
    }
}