package com.example.fitnesstracker.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.inspectable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.ui.components.subComponents.Placement
import com.example.fitnesstracker.ui.components.subComponents.Shadow
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Column (modifier = modifier.fillMaxWidth()){
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleLarge
                )
            },
        )
        Shadow(elevation = 4.dp, placement = Placement.bottom)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWorkoutTopBar(topBarText: String, onBackClick: () -> Unit, onSaveClick: () -> Unit, modifier: Modifier = Modifier, @StringRes btnText: Int) {
    Column (modifier = modifier.fillMaxWidth()){
        TopAppBar(
            title = {
                Text(
                    text = topBarText,
                    style = MaterialTheme.typography.titleLarge,
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(painter = painterResource(R.drawable.arrow_left), contentDescription = null)
                }
            },
            actions = {
                Button(onClick = onSaveClick) {
                    Text(text = stringResource(btnText))
                }
            }
        )
        // Shadow
        Shadow(elevation = 4.dp, placement = Placement.bottom)
    }
}

@Preview
@Composable
fun TopBarPreview() {
    FitnessTrackerTheme {
        Surface {
            //CreateWorkoutTopBar({}, {})
        }
    }
}