package com.example.fitnesstracker.ui.exercise

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.dao.MusclesWithNumberOfExercises
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.Muscle
import com.example.fitnesstracker.ui.components.ExerciseCard
import com.example.fitnesstracker.ui.components.MuscleCard
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@Composable
fun ExercisesScreen(modifier: Modifier = Modifier, exerciseScreenViewModel: ExerciseScreenViewModel = hiltViewModel()) {

    val screenState = exerciseScreenViewModel.exercisesScreenState.collectAsStateWithLifecycle()

    val allMuscles = exerciseScreenViewModel.musclesWithNumberOfExercises.collectAsState(initial = emptyList())

    val exercises = exerciseScreenViewModel.getAllExercisesByMuscles(muscle = screenState.value.selectedMuscle).collectAsState(initial = emptyList())

    if(screenState.value.selectedMuscle != Muscle()){
        BackHandler {
            exerciseScreenViewModel.setSelectedMuscle(Muscle())
        }
    }

    if (screenState.value.selectedMuscle == Muscle())
        ContentMuscles(exerciseScreenViewModel = exerciseScreenViewModel , allMusclesState = allMuscles, modifier)
    else
        ContentExercises(exerciseScreenViewModel = exerciseScreenViewModel, exercises = exercises, modifier)

}

@Composable
fun ContentMuscles(exerciseScreenViewModel: ExerciseScreenViewModel, allMusclesState:  State<List<MusclesWithNumberOfExercises>>, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(allMusclesState.value) {
            MuscleCard(
                onClick = {
                          exerciseScreenViewModel.setSelectedMuscle(it.muscle)
                },
                icon = R.drawable.user,
                muscleName = it.muscle.name,
                numberOfExercises = it.numberOfExercises
            )
        }
    }
}

@Composable
fun ContentExercises(exerciseScreenViewModel: ExerciseScreenViewModel, exercises: State<List<Exercise>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(exercises.value) {
            ExerciseCard(icon = R.drawable.user, exerciseName = it.name)
        }
    }
}

@Preview
@Composable
fun ExercisesScreenPreview() {
    FitnessTrackerTheme {
        Surface {
            //ExercisesScreen({}, Modifier.fillMaxSize())
        }
    }
}