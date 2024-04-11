package com.example.fitnesstracker

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesstracker.ui.home.HomeScreen
import com.example.fitnesstracker.ui.components.NavBar
import com.example.fitnesstracker.ui.components.TopBar
import com.example.fitnesstracker.ui.exercise.ExercisesScreen
import com.example.fitnesstracker.ui.home.createworkout.CreateWorkoutScreen
import com.example.fitnesstracker.ui.home.workout.WorkoutScreen
import com.example.fitnesstracker.ui.navigation.NavigationViewModel
import dagger.hilt.android.HiltAndroidApp

enum class FitnessTrackerAppScreens{
    Home,
    Exercises,
    About,
    CreateWorkout,
    Workout,
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FitnessTrackerApp(navController: NavHostController = rememberNavController(), navigationViewModel: NavigationViewModel = viewModel<NavigationViewModel>()) {

    val currentScreen = navigationViewModel.currentScreen.value

    Scaffold (
        topBar = {
            if (currentScreen.name != FitnessTrackerAppScreens.Home.name &&
                currentScreen.name != FitnessTrackerAppScreens.Exercises.name &&
                currentScreen.name != FitnessTrackerAppScreens.About.name) return@Scaffold
            TopBar()
        },
        bottomBar = {
            if (currentScreen.name != FitnessTrackerAppScreens.Home.name &&
                currentScreen.name != FitnessTrackerAppScreens.Exercises.name &&
                currentScreen.name != FitnessTrackerAppScreens.About.name) return@Scaffold
            NavBar(
                navigationViewModel = navigationViewModel,
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
                navigationViewModel.updateCurrentScreen(FitnessTrackerAppScreens.Home)
                HomeScreen(
                    onStartWorkout = {
                        navController.navigate("${FitnessTrackerAppScreens.Workout.name}/$it")
                    },
                    onCreateWorkoutClick = {
                        navController.navigate(route = FitnessTrackerAppScreens.CreateWorkout.name)
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }

            composable(
                route = "${FitnessTrackerAppScreens.Workout.name}/{workoutId}",
                arguments = listOf(
                    navArgument("workoutId") {
                        type = NavType.IntType
                    }
                )
            ) {
                navigationViewModel.updateCurrentScreen(FitnessTrackerAppScreens.CreateWorkout)
                WorkoutScreen(
                    //workoutId = it.arguments?.getInt("workoutId") ?: 0,
                    onNavigateUp = {
                        navController.navigateUp()
                    }
                )
            }

            composable(route = FitnessTrackerAppScreens.CreateWorkout.name) {
                navigationViewModel.updateCurrentScreen(FitnessTrackerAppScreens.CreateWorkout)
                CreateWorkoutScreen(
                    onNavigateUp = {
                        navController.navigateUp()
                    }
                )
            }

            // Exercises Screen
            composable(route = FitnessTrackerAppScreens.Exercises.name) {
                navigationViewModel.updateCurrentScreen(FitnessTrackerAppScreens.Exercises)
                ExercisesScreen(modifier = Modifier.padding(paddingValues))
            }

            composable(route = FitnessTrackerAppScreens.About.name) {
                navigationViewModel.updateCurrentScreen(FitnessTrackerAppScreens.About)
            }

        }
    }
}