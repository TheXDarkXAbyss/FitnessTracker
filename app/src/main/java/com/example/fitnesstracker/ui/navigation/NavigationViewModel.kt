package com.example.fitnesstracker.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.fitnesstracker.FitnessTrackerAppScreens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavigationViewModel(): ViewModel() {

    private val _navigationState = MutableStateFlow(NavigationState())
    val navigationState: StateFlow<NavigationState> = _navigationState.asStateFlow()


    fun updateCurrentScreen(currentScreen: FitnessTrackerAppScreens) {

        _navigationState.update {
            it.copy(
                currentScreen = currentScreen
            )
        }

    }

}