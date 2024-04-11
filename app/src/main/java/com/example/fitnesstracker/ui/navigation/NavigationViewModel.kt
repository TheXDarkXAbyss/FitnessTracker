package com.example.fitnesstracker.ui.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.fitnesstracker.FitnessTrackerAppScreens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavigationViewModel(): ViewModel() {

    var currentScreen = mutableStateOf<FitnessTrackerAppScreens>(FitnessTrackerAppScreens.Home)
        private set

    fun updateCurrentScreen(currentScreen: FitnessTrackerAppScreens) {
        this.currentScreen.value = currentScreen
    }

}
