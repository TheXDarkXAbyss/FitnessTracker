package com.example.fitnesstracker.ui.exercise

import com.example.fitnesstracker.data.entity.Muscle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ExercisesScreenState(
    val selectedMuscle: Muscle = Muscle(),
)