package com.example.fitnesstracker

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.ui.HomeScreen
import com.example.fitnesstracker.ui.components.NavBar
import com.example.fitnesstracker.ui.components.TopBar
import com.example.fitnesstracker.ui.exercise.ExercisesScreen
import com.example.fitnesstracker.ui.navigation.NavigationViewModel

enum class FitnessTrackerAppScreens{
    Home,
    Exercises,
    About
}

@Composable
fun FitnessTrackerApp(navController: NavHostController = rememberNavController()) {

    Scaffold (
        topBar = {
            TopBar()
        },
        bottomBar = {
            NavBar(
                navigationViewModel = viewModel<NavigationViewModel>(),
                navHostController = navController
            )
        },
        modifier = Modifier
            .fillMaxSize()
    ){paddingValues ->
        NavHost(navController = navController,
            startDestination = FitnessTrackerAppScreens.Home.name,
            // No Animation
            enterTransition = {
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
            },
            // ------------------------------------------------------------------
            modifier = Modifier
                .fillMaxSize()
        ) {

            /* *** App Routes *** */

            // Home Screen
            composable(route = FitnessTrackerAppScreens.Home.name) {
                HomeScreen(modifier = Modifier.padding(paddingValues))
            }

            // Exercises Screen
            composable(route = FitnessTrackerAppScreens.Exercises.name) {
                ExercisesScreen(modifier = Modifier.padding(paddingValues))
            }

            composable(route = FitnessTrackerAppScreens.About.name) {

            }
        }
    }


}