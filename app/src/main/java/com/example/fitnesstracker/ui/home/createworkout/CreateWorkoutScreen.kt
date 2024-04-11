package com.example.fitnesstracker.ui.home.createworkout

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.dao.MusclesWithNumberOfExercises
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.Muscle
import com.example.fitnesstracker.ui.components.CreateWorkoutTopBar
import com.example.fitnesstracker.ui.components.ExerciseCard
import com.example.fitnesstracker.ui.components.ExerciseCardWithButton
import com.example.fitnesstracker.ui.components.MuscleCard
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@Composable
fun CreateWorkoutScreen(onNavigateUp: () -> Unit, modifier: Modifier = Modifier, createWorkoutScreenViewModel: CreateWorkoutScreenViewModel = hiltViewModel()) {

    val createWorkoutState = createWorkoutScreenViewModel.createWorkoutScreenState.collectAsState()
    val selectedMuscle = createWorkoutScreenViewModel.selectedMuscle.collectAsState().value

    BackHandler {
        if (createWorkoutState.value.isSelectingExercise && selectedMuscle != Muscle()) {
            createWorkoutScreenViewModel.setSelectedMuscle(Muscle())
        }
        else if (createWorkoutState.value.isSelectingExercise && selectedMuscle == Muscle()) {
            createWorkoutScreenViewModel.setIsSelectingExercise(false)
        }
        else {
            onNavigateUp()
        }
    }

    Scaffold (
        topBar = {
            CreateWorkoutTopBar(
                topBarText = stringResource(R.string.createWorkout),
                onBackClick = {
                    if (createWorkoutState.value.isSelectingExercise && selectedMuscle != Muscle()) {
                        createWorkoutScreenViewModel.setSelectedMuscle(Muscle())
                    }
                    else if (createWorkoutState.value.isSelectingExercise && selectedMuscle == Muscle()) {
                        createWorkoutScreenViewModel.setIsSelectingExercise(false)
                    }
                    else {
                        onNavigateUp()
                    }
                              },
                onSaveClick = {
                    createWorkoutScreenViewModel.saveWorkout()
                    //onNavigateUp()
                },
                btnText = R.string.btnSave
            )
        },
    ) {

        if (createWorkoutState.value.isSelectingExercise)
        {
            SelectExercise(
                selectedMuscle = selectedMuscle,
                onMuscleSelect = {muscle ->
                                 createWorkoutScreenViewModel.setSelectedMuscle(muscle)
                },
                muscleList = createWorkoutScreenViewModel.musclesWithNumberOfExercises.collectAsState(initial = emptyList()).value,
                exerciseList = createWorkoutScreenViewModel.getAllExercisesByMuscles(selectedMuscle).collectAsState(initial = emptyList()).value,
                onExerciseSelect = { exercise ->
                    createWorkoutScreenViewModel.addExerciseToWorkout(exercise)
                    createWorkoutScreenViewModel.setIsSelectingExercise(false)
                    createWorkoutScreenViewModel.setSelectedMuscle(Muscle())
                },
                padding = it
            )
            return@Scaffold
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = it,
            verticalArrangement = if (createWorkoutState.value.exercisesList == emptyList<Exercise>()) Arrangement.Center else Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(createWorkoutState.value.exercisesList) {exercise ->
                ExerciseCard(icon = R.drawable.user, exerciseName = exercise.name)
            }
            
            item {
                if (createWorkoutState.value.exercisesList == emptyList<Exercise>())
                {
                    Text(
                        text = stringResource(R.string.addFirstExercise),
                        modifier = modifier.padding(bottom = 16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                Button(
                    onClick = {
                        createWorkoutScreenViewModel.setIsSelectingExercise(true)
                    }
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(R.drawable.add), contentDescription = null)
                        Text(text = stringResource(R.string.btnAddExercises))
                    }
                }
            }
        }

    }
}

@Composable
fun SelectExercise(
    selectedMuscle: Muscle,
    onMuscleSelect: (Muscle) -> Unit,
    muscleList: List<MusclesWithNumberOfExercises>,
    exerciseList: List<Exercise>,
    onExerciseSelect: (Exercise) -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = padding,
    ) {
        if (selectedMuscle == Muscle()) {
            items(muscleList) {muscleWithNumber ->
                MuscleCard(
                    onClick = {
                        onMuscleSelect(muscleWithNumber.muscle)
                              },
                    icon = R.drawable.user,
                    muscleName = muscleWithNumber.muscle.name,
                    numberOfExercises = muscleWithNumber.numberOfExercises
                )
            }
        }
        else {
            items(exerciseList) { exercise ->
                ExerciseCardWithButton(
                    icon = R.drawable.user,
                    exerciseName = exercise.name,
                    onButtonClick = {
                        onExerciseSelect(exercise)
                    },
                    buttonText = R.string.btnAdd
                )
            }
        }

    }
}

@Preview
@Composable
private fun CreateWorkoutScreenPreview(modifier: Modifier = Modifier) {
    FitnessTrackerTheme {
        Surface {
            SelectExercise(
                selectedMuscle = Muscle(1, "ass"),
                onMuscleSelect = {},
                muscleList = listOf(
                    MusclesWithNumberOfExercises(muscle = Muscle(1,"sss"), 5),
                    MusclesWithNumberOfExercises(muscle = Muscle(1,"sss"), 5)
                ),
                exerciseList = listOf(
                    Exercise(id = 1, name = "asda", targetedMuscleId = 1),
                    Exercise(id = 1, name = "adasd", targetedMuscleId = 1),
                    Exercise(id = 1, name = "asdasd", targetedMuscleId = 1),
                    Exercise(id = 1, name = "asdasddfg", targetedMuscleId = 1)
                ),
                onExerciseSelect = {},
                padding = PaddingValues()
            )
        }
    }
}