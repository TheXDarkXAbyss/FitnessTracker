package com.example.fitnesstracker.extratools

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.customBorder(start: Float = 0f, top: Float = 0f, end: Float = 0f, bottom: Float = 0f, color: Color = Color.Black) = composed (
    factory = {

        this.drawBehind {

            // Start
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                strokeWidth = start * density
            )

            // Top
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = top * density
            )

            // End
            drawLine(
                color = color,
                start = Offset(size.width, 0f),
                end = Offset(size.width, size.height),
                strokeWidth = end * density
            )

            // Bottom
            drawLine(
                color = color,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = bottom * density
            )
        }
    }
)