package com.example.fitnesstracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.example.fitnesstracker.ui.theme.White

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    //EmptyHomeScreen(modifier)
    HomeScreenWithContent(modifier)
}

@Composable
fun EmptyHomeScreen(modifier: Modifier = Modifier) {
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
            onClick = { /*TODO*/ }
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
fun HomeScreenWithContent( modifier: Modifier = Modifier) {
    LazyColumn() {

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    FitnessTrackerTheme {
        Surface {
            HomeScreen()
        }
    }
}