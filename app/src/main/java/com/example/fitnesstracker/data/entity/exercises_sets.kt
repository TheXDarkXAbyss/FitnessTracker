package com.example.fitnesstracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises_sets",
    foreignKeys = [
        ForeignKey(entity = WorkoutExercises::class, parentColumns = ["id"], childColumns = ["workout_exercise_id"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = Exercise ::class, parentColumns = ["id"], childColumns = ["exercise_id"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Session::class, parentColumns = ["id"], childColumns = ["session_id"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.NO_ACTION),
    ]
)
data class ExercisesSets(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "workout_exercise_id")
    val workoutExerciseId: Int = 0,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int = 0,
    @ColumnInfo(name = "session_id")
    val sessionId: Int = 0,
    @ColumnInfo(name = "set_number")
    val setNumber: Int = 0,
    @ColumnInfo(name = "reps")
    val reps: Int = 0,
    @ColumnInfo(name = "weight")
    val weight: Int = 0,
    @ColumnInfo(name = "difficulty")
    val difficulty: Int = 0,

)
