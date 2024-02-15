package com.example.fitnesstracker.ui.exercise

import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.data.Muscle
import com.example.fitnesstracker.ui.navigation.NavigationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExerciseScreenViewModel: ViewModel() {

    private val _exerciseScreenState = MutableStateFlow(ExercisesScreenState())
    val exerciseScreenState: StateFlow<ExercisesScreenState> = _exerciseScreenState.asStateFlow()

    fun setSelectedMuscle(muscle: Muscle?) {
        _exerciseScreenState.update {
            it.copy(
                selectedMuscle = muscle
            )
        }
    }

}