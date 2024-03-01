package com.example.fitnesstracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "workout_name")
    val name: String = "",
    @ColumnInfo(name = "deleted")
    val deleted: Boolean = false
)
