package com.example.fitnesstracker.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.entity.Workout
import com.example.fitnesstracker.ui.components.WorkoutCard
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.example.fitnesstracker.ui.theme.White


@Composable
fun HomeScreen(onStartWorkout: (workoutId: Int) -> Unit,onCreateWorkoutClick: () -> Unit, modifier: Modifier = Modifier, homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {

    val workouts = homeScreenViewModel.getAllWorkouts().collectAsState(initial = emptyList())

    if (workouts.value == emptyList<Workout>())
        EmptyHomeScreen(onCreateWorkoutClick = onCreateWorkoutClick, modifier = modifier)
    else
        HomeScreenWithContent(onStartWorkout = onStartWorkout, onCreateWorkoutClick = onCreateWorkoutClick, workouts = workouts.value, modifier = modifier)

}

@Composable
fun EmptyHomeScreen(onCreateWorkoutClick: () -> Unit, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.empty_homeScreenTitle),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.empty_homeScreenSubtitle),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp, bottom = 16.dp)
        )

        Button(
            onClick = onCreateWorkoutClick
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.add),
                    contentDescription = null,
                    tint = White
                )

                Text(
                    text = stringResource(R.string.btnCreateFirstWorkout),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

    }
}

@Composable
fun HomeScreenWithContent(onStartWorkout: (workoutId: Int) -> Unit, onCreateWorkoutClick: () -> Unit, workouts: List<Workout>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(workouts) {
            WorkoutCard(
                workoutName = it.name,
                numberOfExercises = 5,
                onClick = {
                    onStartWorkout(it.id)
                }
            )
        }
        
        item {
            Button(
                onClick = onCreateWorkoutClick
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(R.drawable.add), contentDescription = null)
                    Text(text = stringResource(R.string.btnAddNewWorkout))
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    FitnessTrackerTheme {
        Surface {
            //HomeScreenWithContent(onCreateWorkoutClick = {}, workouts = emptyList(), modifier = Modifier.fillMaxSize())
        }
    }
}