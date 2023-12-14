package com.kursatkumsuz.movie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kursatkumsuz.movie.common.BottomNavigationBar
import com.kursatkumsuz.movie.navigation.NavGraph
import com.kursatkumsuz.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.kursatkumsuz.ui.theme.MovieTheme {
                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route


                var bottomBarState by rememberSaveable { (mutableStateOf(false)) }

                bottomBarState = when (currentRoute) {
                    Screen.HomeScreen.route -> true
                    Screen.WatchListScreen.route -> true
                    Screen.SearchScreen.route -> true
                    Screen.ProfileScreen.route -> true
                    else -> false
                }
                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(visible = bottomBarState) {
                            BottomNavigationBar(
                                onItemClick = {
                                    navController.navigate(it) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    com.kursatkumsuz.ui.theme.MovieTheme {
    }
}