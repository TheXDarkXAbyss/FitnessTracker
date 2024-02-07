package com.example.fitnesstracker.ui.components.subComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Shadow(elevation: Dp, placement: Placement = Placement.bottom) {
    if(placement == Placement.bottom) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0, 0, 0, 25),
                            Color(0, 0, 0, 0)
                        )
                    )
                )
        )
    }
    when (placement) {
        Placement.bottom -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(elevation)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0, 0, 0, 25),
                                Color(0, 0, 0, 0)
                            )
                        )
                    )
            )
        }
        Placement.top -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(elevation)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0, 0, 0, 0),
                                Color(0, 0, 0, 25)
                            )
                        )
                    )
            )
        }
        Placement.right -> {
            Box(
                modifier = Modifier
                    .width(elevation)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0, 0, 0, 25),
                                Color(0, 0, 0, 0)
                            )
                        )
                    )
            )
        }
        Placement.left -> {
            Box(
                modifier = Modifier
                    .width(elevation)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0, 0, 0, 0),
                                Color(0, 0, 0, 25)
                            )
                        )
                    )
            )
        }
    }
}

enum class Placement {
    top,
    bottom,
    right,
    left
}