package com.example.fitnesstracker.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@Composable
fun ExercisesMuscleScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {

    }
}


@Preview
@Composable
fun ExercisesMuscleScreenPreview() {
    FitnessTrackerTheme {
        Surface {
            ExercisesMuscleScreen()
        }
    }
}