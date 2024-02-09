package com.example.fitnesstracker

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.ui.AboutScreen
import com.example.fitnesstracker.ui.ExercisesMuscleScreen
import com.example.fitnesstracker.ui.ExercisesScreen
import com.example.fitnesstracker.ui.HomeScreen
import com.example.fitnesstracker.ui.components.NavBar
import com.example.fitnesstracker.ui.components.TopBar
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.example.fitnesstracker.ui.viewmodel.NavBarViewModel


enum class FitnessTrackerScreen {
    Exercises,
    ExercisesMuscleSpecific,
    Home,
    About
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FitnessTrackerApp(navController: NavHostController = rememberNavController(), navBarViewModel: NavBarViewModel = NavBarViewModel()) {

    val navbarState by navBarViewModel.navbarState.collectAsState()


    Scaffold (
        topBar = {
            TopBar()
        },
        bottomBar = {
            NavBar(
                onClickBtnExercise = {
                    /*
                    * navigate to Exercises screen.
                    * clicking again on the button will close any sub screen open returning the user to the Exercises screen.
                    * the Exercises screen will be rested in this case.
                    * */
                    navController.navigate(FitnessTrackerScreen.Exercises.name) {
                        popUpTo(FitnessTrackerScreen.Exercises.name) {
                            inclusive = true
                        }
                    }
                },
                onClickBtnHome = {
                    navController.popBackStack(FitnessTrackerScreen.Home.name, inclusive = false)
                },
                onClickBtnAbout = {
                    /*
                    * navigate to About screen.
                    * clicking again on the button will close any sub screen open returning the user to the About screen.
                    * the About screen will be rested in this case.
                    * */
                    navController.navigate(FitnessTrackerScreen.About.name) {
                        popUpTo(FitnessTrackerScreen.About.name) {
                            inclusive = true
                        }
                    }
                },
                currentScreen = navbarState.currentScreen
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = FitnessTrackerScreen.Home.name,
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
            }
        ) {

            composable(
                route = FitnessTrackerScreen.Home.name,
            ) {
                // update current screen
                navBarViewModel.setCurrentScreen(FitnessTrackerScreen.Home)

                // navigating back will return the user to the Home screen
                BackHandler {
                    navController.popBackStack(FitnessTrackerScreen.Home.name, inclusive = false)
                }

                HomeScreen()
            }

            composable(
                route = FitnessTrackerScreen.Exercises.name,
            ) {
                // update current screen
                navBarViewModel.setCurrentScreen(FitnessTrackerScreen.Exercises)
                ExercisesScreen(
                    {
                        navController.navigate(FitnessTrackerScreen.ExercisesMuscleSpecific.name)
                    },
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )
            }

            composable(
                route = FitnessTrackerScreen.About.name,
            ) {
                // update current screen
                navBarViewModel.setCurrentScreen(FitnessTrackerScreen.About)

                // navigating back will return the user to the Home screen
                BackHandler {
                    navController.popBackStack(FitnessTrackerScreen.Home.name, inclusive = false)
                }

                AboutScreen(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize())
            }

            composable(
                route = FitnessTrackerScreen.ExercisesMuscleSpecific.name
            ) {
                ExercisesMuscleScreen(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                )
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