package com.example.fitnesstracker.data.database.entitie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "workouts_sets",
    foreignKeys = [
        ForeignKey(entity = WorkoutExercise::class, parentColumns = ["id"], childColumns = ["workout_exercise_id_fk"],
            onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        ForeignKey(entity = Session::class, parentColumns = ["id"], childColumns = ["session_id_fk"],
            onDelete = ForeignKey.SET_NULL, onUpdate = ForeignKey.CASCADE)
    ]
)
data class WorkoutSet(
    //@ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "workout_exercise_id_fk")
    val workoutExerciseId: Int,
    @ColumnInfo(name = "session_id_fk")
    val sessionId: Int,
    val reps: Float,
    val weight: Float
)
