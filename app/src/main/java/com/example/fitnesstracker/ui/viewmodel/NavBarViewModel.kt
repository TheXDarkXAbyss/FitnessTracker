package com.example.fitnesstracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.FitnessTrackerScreen
import com.example.fitnesstracker.data.state.NavbarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavBarViewModel: ViewModel() {
    private val _navBarState = MutableStateFlow(NavbarState())
    val navbarState: StateFlow<NavbarState> = _navBarState.asStateFlow()

    fun setCurrentScreen(currentScreen: FitnessTrackerScreen) {
        _navBarState.update {
            it.copy(
                currentScreen = currentScreen
            )
        }
    }
}