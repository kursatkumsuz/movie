package com.kursatkumsuz.movie.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kursatkumsuz.movie.util.BottomNavItem
import com.kursatkumsuz.util.Screen


@ExperimentalAnimationApi
@Composable
fun BottomNavigationBar(
    onItemClick: (String) -> Unit
) {
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val items = listOf(
        BottomNavItem(
            "Home",
            Screen.HomeScreen.route,
            Icons.Default.Home
        ),
        BottomNavItem(
            "Watchlist",
            Screen.WatchListScreen.route,
            Icons.Default.List
        ),
        BottomNavItem(
            "Search",
            Screen.SearchScreen.route,
            Icons.Default.Search
        ),
        BottomNavItem(
            "Profile",
            Screen.ProfileScreen.route,
            Icons.Default.Person
        )
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.secondary,
        elevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            key(item.name) {
                val selected = selectedIndex == index
                BottomNavigationItem(
                    selected = selected,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                    onClick = {
                        selectedIndex = index
                        onItemClick(item.route)
                    },
                    icon = {
                        AnimatedVisibility(
                            visible = selected,
                            enter = fadeIn() + scaleIn(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.tertiary),
                            ) {
                                Row(
                                    modifier = Modifier.padding(
                                        vertical = 5.dp,
                                        horizontal = 10.dp
                                    ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.name,
                                    )
                                    Divider(modifier = Modifier.width(5.dp))
                                    Text(text = item.name, fontSize = 12.sp)
                                }
                            }
                        }
                        AnimatedVisibility(
                            visible = !selected,
                        ) {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                    })
            }
        }
    }
}


