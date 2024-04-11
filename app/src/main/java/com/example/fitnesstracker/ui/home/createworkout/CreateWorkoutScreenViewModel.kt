package com.example.fitnesstracker.ui.home.createworkout

import android.provider.ContactsContract.Intents.Insert
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.Muscle
import com.example.fitnesstracker.data.entity.Workout
import com.example.fitnesstracker.data.entity.WorkoutExercises
import com.example.fitnesstracker.ui.exercise.ExerciseScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWorkoutScreenViewModel @Inject constructor(private val exerciseScreenRepository: ExerciseScreenRepository, private val createWorkoutRepository: CreateWorkoutRepository) : ViewModel() {

    private val _createWorkoutScreenState = MutableStateFlow(CreateWorkoutScreenState())
    val createWorkoutScreenState = _createWorkoutScreenState.asStateFlow()

    private val _selectedMuscle = MutableStateFlow(Muscle())
    val selectedMuscle = _selectedMuscle.asStateFlow()

    val musclesWithNumberOfExercises = exerciseScreenRepository.getMusclesWithNumberOfExercises()

    fun setSelectedMuscle(muscle: Muscle) {
        _selectedMuscle.update {
            it.copy(
                id = muscle.id,
                name = muscle.name
            )
        }
    }

    fun addExerciseToWorkout(exercise: Exercise) {
        val newList = _createWorkoutScreenState.value.exercisesList.toMutableList()
        newList.add(exercise)

        _createWorkoutScreenState.update {
            it.copy(
                exercisesList = newList
            )
        }
    }

    fun setIsSelectingExercise(isSelecting: Boolean) {
        _createWorkoutScreenState.update {
            it.copy(
                isSelectingExercise = isSelecting
            )
        }
    }

    fun saveWorkout() {
        viewModelScope.launch {
            val workoutId = createWorkoutRepository.insertWorkout(workout = Workout(name ="WorkoutName"))
            Log.e("testing","workout saved")
            Log.e("testing","id got $workoutId")
            val workoutExercisesList: MutableList<WorkoutExercises> = mutableListOf()
            createWorkoutScreenState.value.exercisesList.forEach {
                workoutExercisesList.add(
                    WorkoutExercises(
                        exerciseId = it.id,
                        workoutId = workoutId.toInt()
                    )
                )
            }
            Log.e("testing","exercises complete")

            createWorkoutRepository.insertWorkoutExercises(workoutExercisesList)
            Log.e("testing","exercises saved")
        }
    }

    fun getAllExercisesByMuscles(muscle: Muscle) = exerciseScreenRepository.getExercisesByMuscle(muscleId = muscle.id)

}

data class CreateWorkoutScreenState(
    val isSelectingExercise: Boolean = false,
    val exercisesList: List<Exercise> = emptyList()
)