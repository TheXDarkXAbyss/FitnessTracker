package com.example.fitnesstracker.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.Muscle
import com.example.fitnesstracker.ui.components.MuscleCard
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@Composable
fun ExercisesScreen(onCardClick: () -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 16.dp)
    ) {
        val muscles = Muscle().getAll()

        items(muscles) {
            MuscleCard(
                {
                    onCardClick()
                },
                icon = R.drawable.user,
                muscleName = it,
                numberOfExercises = 10)
        }
    }
}



@Preview
@Composable
fun ExercisesScreenPreview() {
    FitnessTrackerTheme {
        Surface {
            ExercisesScreen({}, Modifier.fillMaxSize())
        }
    }
}