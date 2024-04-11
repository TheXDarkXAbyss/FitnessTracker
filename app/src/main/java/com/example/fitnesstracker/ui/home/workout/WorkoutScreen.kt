package com.example.fitnesstracker.ui.home.workout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.dao.WorkoutExercise
import com.example.fitnesstracker.ui.components.ClickableExerciseCard
import com.example.fitnesstracker.ui.components.CreateWorkoutTopBar
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.example.fitnesstracker.ui.theme.Gray2

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutScreen(onNavigateUp: () -> Unit, workoutScreenViewModel: WorkoutScreenViewModel = hiltViewModel()) {

    val workoutExercises = workoutScreenViewModel.workoutExercises.collectAsState(initial = emptyList<WorkoutExercise>()).value

    Scaffold(
        topBar = {
            CreateWorkoutTopBar(topBarText = "WorkoutName", onBackClick = onNavigateUp, onSaveClick = {
                                                                                                      workoutScreenViewModel.save()
                onNavigateUp()
            }, btnText = R.string.btnFinish)
        }
    ) {
        LazyColumn(
            contentPadding = it
        ) {
            // adds the exercises to the screen body
            items(workoutExercises) {exerciseDetail ->
                ClickableExerciseCard(
                    onCardClick = {
                        workoutScreenViewModel.setSelectedExercise(workoutExercise = exerciseDetail)
                        workoutScreenViewModel.openSetsDialog()
                    },
                    icon = R.drawable.user,
                    exerciseName = exerciseDetail.exercise.name
                )
            }

            item {
                if (workoutScreenViewModel.isSetsDialogOpen.value) {
                    SetsCard(
                        onDismiss = {
                            workoutScreenViewModel.closeSetsDialog()
                                    },
                        exerciseName = "name",
                        previousSets = workoutScreenViewModel.previousSetData,
                        txtRepsValue = workoutScreenViewModel.txtRepsValues,
                        txtRepsOnValueChange = { index, value ->
                            workoutScreenViewModel.updateTxtRepsValues(index, value)
                                               },
                        txtWeightValue = workoutScreenViewModel.txtWeightValues,
                        txtWeightOnValueChange = { index, value ->
                            workoutScreenViewModel.updateTxtWeightValues(index, value)
                        },
                        btnDifficultyValue = workoutScreenViewModel.btnSelectedButtonDifficulty,
                        btnDifficultyChange = { index, value ->
                            workoutScreenViewModel.updateBtnSelectedButtonDifficulty(index, value)
                        }
                    )
                }
            }
        }
    }

}

@Composable
private fun SetsCard(
    onDismiss: () -> Unit,
    exerciseName: String,
    previousSets: List<String>,
    txtRepsValue: List<String>,
    txtRepsOnValueChange: (index:Int, value:String) -> Unit,
    txtWeightValue: List<String>,
    txtWeightOnValueChange: (index:Int, value:String) -> Unit,
    btnDifficultyValue: List<Int>,
    btnDifficultyChange: (index:Int, value:Int) -> Unit,
    modifier: Modifier = Modifier) {

    Dialog(onDismissRequest = onDismiss) {

        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = exerciseName,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Icon(painter = painterResource(R.drawable.minimize), contentDescription = "minimize", modifier = Modifier.clickable { onDismiss() })

                }

                Divider(color = Color.Black)

                for (x in 0..<txtRepsValue.count()) {
                    Set(
                        setNumber = x+1,
                        previousSet = previousSets[x],
                        repsValue = txtRepsValue[x],
                        onValueChangeOne = {
                            txtRepsOnValueChange(x, it)
                        },
                        weightValue = txtWeightValue[x],
                        onValueChangeTwo = {
                            txtWeightOnValueChange(x, it)
                        },
                        difficultyValue = btnDifficultyValue[x],
                        difficultyClick = {
                            btnDifficultyChange(x, it)
                        }
                    )
                    Divider(Modifier.fillMaxWidth(0.75f))
                }
            }
        }

    }
}



@Composable
private fun Set(
    setNumber: Int,
    previousSet: String,
    repsValue: String,
    weightValue: String,
    onValueChangeOne: (String) -> Unit,
    onValueChangeTwo: (String) -> Unit,
    difficultyValue: Int,
    difficultyClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 16.dp),
    ) {
        Row (
            modifier = modifier
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Set $setNumber",
                modifier = Modifier.weight(0.25f),
                style = MaterialTheme.typography.bodySmall
            )

            CenteredText(text = previousSet, modifier = Modifier.weight(0.25f))

            CustomTextField(value = repsValue, onValueChange = onValueChangeOne , modifier = Modifier.weight(0.25f))
            CustomTextField(value = weightValue, onValueChange = onValueChangeTwo, modifier = Modifier.weight(0.25f))

        }

        Row (
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Difficulty",
                modifier = Modifier.weight(0.25f),
                style = MaterialTheme.typography.bodySmall
            )

            Row(
                modifier = Modifier.weight(0.75f),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            ) {
                for (x in 0..4) {
                    ClickableText(
                        text = (x+1).toString(),
                        backgroundColor = if (x == difficultyValue - 1) MaterialTheme.colorScheme.primary else Gray2,
                        onClick = {
                            difficultyClick(x+1)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CenteredText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun CustomTextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    val maxCharacters = 4

    BasicTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxCharacters) onValueChange(it)
                        },
        modifier = modifier
            .border(
                BorderStroke(1.dp, Color(0f, 0f, 0f, 0.5f)),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(12.dp)
            .border(
                BorderStroke(1.dp, Color(0f, 0f, 0f, 0.0f)),
                shape = RoundedCornerShape(6.dp)
            ),

        textStyle = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
private fun ClickableText(text: String, backgroundColor: Color, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        color = Color.Black,
        style = MaterialTheme.typography.labelSmall,

        )
}

@Preview
@Composable
fun CardPreview() {
    FitnessTrackerTheme {
        Surface {
            //ExerciseCard(onDismiss = { /*TODO*/ }, Exercise())
        }
    }
}

