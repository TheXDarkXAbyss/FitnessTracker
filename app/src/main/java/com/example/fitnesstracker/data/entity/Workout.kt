package com.example.fitnesstracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts") // annotating the the class entity tells room that this class is a table
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "workout_name") // the name parameter sets the name of the column in the database
    val name: String = "",
    @ColumnInfo(name = "deleted")
    val deleted: Boolean = false
)
