package com.example.fitnesstracker.data

import androidx.annotation.DrawableRes

data class Exercise(
    @DrawableRes val icon: Int,
    val name: String = "",
    val description: String = ""
) {

}
