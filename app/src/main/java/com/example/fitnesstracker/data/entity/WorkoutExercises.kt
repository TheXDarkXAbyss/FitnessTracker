package com.example.fitnesstracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_exercises",
    foreignKeys = [
        ForeignKey(entity = Workout::class, parentColumns = ["id"], childColumns = ["workout_id"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)
    ]
)
data class WorkoutExercises(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "workout_id")
    val workoutId: Int = 0,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int = 0
)
