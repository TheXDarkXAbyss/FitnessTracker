package com.example.fitnesstracker

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.ui.AboutScreen
import com.example.fitnesstracker.ui.ExercisesScreen
import com.example.fitnesstracker.ui.HomeScreen
import com.example.fitnesstracker.ui.components.NavBar
import com.example.fitnesstracker.ui.components.TopBar
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.example.fitnesstracker.ui.viewmodel.NavBarViewModel


enum class FitnessTrackerScreen () {
    Exercises,
    Home,
    About
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FitnessTrackerApp(navController: NavHostController = rememberNavController()) {
    Scaffold (
        topBar = {
            TopBar()
        },
        bottomBar = {
            NavBar(
                onClickBtnExercise = {
                    navController.navigate(FitnessTrackerScreen.Exercises.name)
                },
                onClickBtnHome = {
                    navController.navigate(FitnessTrackerScreen.Home.name)
                },
                onClickBtnAbout = {
                    navController.navigate(FitnessTrackerScreen.About.name)
                },
                navBarViewModel = NavBarViewModel()
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = FitnessTrackerScreen.Home.name,
            /*enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }*/
        ) {
            composable(
                route = FitnessTrackerScreen.Home.name,
            ) {
                HomeScreen()
            }

            composable(
                route = FitnessTrackerScreen.Exercises.name,
            ) {
                ExercisesScreen(modifier = Modifier.padding(paddingValues))
            }

            composable(
                route = FitnessTrackerScreen.About.name,
            ) {
                AboutScreen(modifier = Modifier.padding(paddingValues))
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitnessTrackerTheme {
        Surface {
            FitnessTrackerApp()
        }
    }
}