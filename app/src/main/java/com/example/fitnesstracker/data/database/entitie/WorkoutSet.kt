package com.example.fitnesstracker.data.database.entitie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "workouts_sets",
    foreignKeys = [
        ForeignKey(entity = WorkoutExercise::class, parentColumns = ["id"], childColumns = ["workoutExerciseId"],
            onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        ForeignKey(entity = Session::class, parentColumns = ["id"], childColumns = ["sessionId"],
            onDelete = ForeignKey.SET_NULL, onUpdate = ForeignKey.CASCADE)
    ]
)
data class WorkoutSet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "workout_exercise_id")
    val workoutExerciseId: Int,
    @ColumnInfo(name = "session_id")
    val sessionId: Int,
    val reps: Float,
    val weight: Float
)
