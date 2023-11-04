package com.kursatkumsuz.movie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.kursatkumsuz.movie.presentation.component.common.BottomNavigationBar
import com.kursatkumsuz.movie.presentation.screens.navigation.NavGraph
import com.kursatkumsuz.movie.ui.theme.MovieTheme
import com.kursatkumsuz.movie.util.BottomNavItem
import com.kursatkumsuz.movie.util.Constants.MOVIE_ID
import com.kursatkumsuz.movie.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {

                val navController = rememberAnimatedNavController()

                Scaffold(
                    bottomBar = {
                        if (currentRoute(navController) != Screen.SplashScreen.route &&
                            currentRoute(navController) != Screen.OnBoardingScreen.route &&
                            currentRoute(navController) != Screen.DetailScreen.route+"/{$MOVIE_ID}" &&
                            currentRoute(navController) != Screen.AuthenticationScreen.route &&
                            currentRoute(navController) != Screen.SignInScreen.route &&
                            currentRoute(navController) != Screen.SignUpScreen.route
                        ) {
                            BottomNavigationBar(items = listOf(
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
                            ), navController = navController, onItemClick = {
                                navController.popBackStack()
                                navController.navigate(it.route)
                            })
                        }
                    }
                ) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}


@Composable
private fun currentRoute(navController: NavController): String? {
    return navController.currentBackStackEntryAsState().value?.destination?.route
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieTheme {
    }
}