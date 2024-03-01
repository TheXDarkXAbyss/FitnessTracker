package com.example.fitnesstracker.ui.exercise

import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.data.entity.Muscle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ExerciseScreenViewModel @Inject constructor(private val exerciseScreenRepository: ExerciseScreenRepository): ViewModel() {

    private val _exercisesScreenState = MutableStateFlow(ExercisesScreenState())
    val exercisesScreenState = _exercisesScreenState.asStateFlow()

    val musclesWithNumberOfExercises = exerciseScreenRepository.getMusclesWithNumberOfExercises()

    init {
        _exercisesScreenState.value = ExercisesScreenState(
            selectedMuscle = Muscle(),
        )
    }

    fun setSelectedMuscle(muscle: Muscle) {
        _exercisesScreenState.update {
            it.copy(
                selectedMuscle = muscle
            )
        }
    }

    fun getAllExercisesByMuscles(muscle: Muscle) = exerciseScreenRepository.getExercisesByMuscle(muscleId = muscle.id)



}