package com.example.fitnesstracker.ui.components

import android.icu.text.ListFormatter.Width
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.extratools.customBorder
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.example.fitnesstracker.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuscleCard(onClick: () -> Unit, @DrawableRes icon: Int, muscleName: String, numberOfExercises: Int, modifier: Modifier = Modifier) {
    Card (
        onClick = onClick,
        modifier = modifier
            .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(72.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .background(Color(0xFFEDEDED))
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                )
            }

            Column (
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 16.dp)
            ){
                Text(
                    text = muscleName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "$numberOfExercises Exercises",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF7A8990)
                )
            }
        }
    }
}

@Composable
fun ExerciseCard(@DrawableRes icon: Int, exerciseName: String, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
            .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(72.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .background(Color(0xFFEDEDED))
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                )
            }

            Column (
                modifier = Modifier
                    .weight(3f)
            ){
                Text(
                    text = exerciseName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun WorkoutCard(workoutName: String, numberOfExercises: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(72.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Column (modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 0.dp, bottom = 16.dp)){
                Text(
                    text = workoutName,
                    modifier = Modifier.padding(bottom = 4.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "$numberOfExercises Exercises",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF7A8990)
                )
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxHeight()
                    .customBorder(start = 3f, color = MaterialTheme.colorScheme.primary),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),

            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.btnStartWorkout),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        painter = painterResource(R.drawable.arrow_right),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

        }

    }
}

@Preview
@Composable
fun Preview() {
    FitnessTrackerTheme {
        Surface {
            WorkoutCard(workoutName = "Push Workout", numberOfExercises = 6, onClick = { /*TODO*/ })
        }
    }
}