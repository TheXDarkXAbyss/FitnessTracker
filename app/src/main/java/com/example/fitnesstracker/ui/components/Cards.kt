package com.example.fitnesstracker.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.ui.extratools.customBorder
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuscleCard(onClick: () -> Unit, @DrawableRes icon: Int, muscleName: String, numberOfExercises: Int, modifier: Modifier = Modifier) {
    Card (
        onClick = onClick,
        modifier = modifier
            .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(88.dp),
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
            .height(80.dp),
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

            Row (
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 16.dp)
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
fun ClickableExerciseCard(onCardClick: () -> Unit, @DrawableRes icon: Int, exerciseName: String, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
            .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                       onCardClick()
            },
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

            Row (
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 16.dp)
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
fun ExerciseCardWithButton(
    @DrawableRes icon: Int,
    exerciseName: String,
    onButtonClick: () -> Unit,
    @StringRes buttonText: Int,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
            .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(80.dp),
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

            Row (
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = exerciseName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(2f)
                )

                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(start = 4.dp)
                ) {
                    Text(text = stringResource(buttonText))
                }
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
                onClick = onClick,
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
            //WorkoutCard(workoutName = "Push Workout", numberOfExercises = 6, onClick = { /*TODO*/ })
            ExerciseCardWithButton(icon = R.drawable.user, exerciseName = "ssss", onButtonClick = {}, buttonText = R.string.btnAdd)
        }
    }
}