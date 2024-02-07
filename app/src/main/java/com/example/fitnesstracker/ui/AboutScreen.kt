package com.example.fitnesstracker.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(text = "About Screen")
    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    FitnessTrackerTheme {
        Surface {
            AboutScreen()
        }
    }
}