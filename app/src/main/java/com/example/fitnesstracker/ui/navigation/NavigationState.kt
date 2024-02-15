package com.example.fitnesstracker.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.FitnessTrackerAppScreens

data class NavigationState(
    val currentScreen: FitnessTrackerAppScreens = FitnessTrackerAppScreens.Home,
)
