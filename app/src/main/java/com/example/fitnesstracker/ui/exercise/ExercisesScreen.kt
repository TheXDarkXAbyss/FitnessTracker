package com.example.fitnesstracker.ui.exercise

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.Muscle
import com.example.fitnesstracker.data.getExercisesByMuscle
import com.example.fitnesstracker.data.getNumberOfExercisesByMuscle
import com.example.fitnesstracker.ui.components.ExerciseCard
import com.example.fitnesstracker.ui.components.MuscleCard
import com.example.fitnesstracker.ui.components.NavBar
import com.example.fitnesstracker.ui.components.TopBar
import com.example.fitnesstracker.ui.navigation.NavigationViewModel
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@Composable
fun ExercisesScreen(exerciseScreenViewModel: ExerciseScreenViewModel = viewModel<ExerciseScreenViewModel>(), modifier: Modifier = Modifier) {

    Content(exerciseScreenViewModel, modifier = modifier)

}

@Composable
private fun Content(exerciseScreenViewModel: ExerciseScreenViewModel, modifier: Modifier = Modifier) {

    val exerciseScreenState by exerciseScreenViewModel.exerciseScreenState.collectAsState()

    if (exerciseScreenState.selectedMuscle != null) {
        BackHandler {
            exerciseScreenViewModel.setSelectedMuscle(null)
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 16.dp)
    ) {
        val muscles = Muscle.entries.toTypedArray()

        if (exerciseScreenState.selectedMuscle == null) {
            items(muscles) {
                MuscleCard(
                    {
                        exerciseScreenViewModel.setSelectedMuscle(it)
                    },
                    icon = R.drawable.user,
                    muscleName = it.name,
                    numberOfExercises = getNumberOfExercisesByMuscle(it)
                )
            }
        }
        else {

            val exercises = getExercisesByMuscle(exerciseScreenState.selectedMuscle)
            items(exercises) {
                ExerciseCard(icon = R.drawable.user, exerciseName = it.name)
            }
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