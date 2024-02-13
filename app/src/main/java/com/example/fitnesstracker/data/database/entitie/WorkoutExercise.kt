package com.example.fitnesstracker.data.database.entitie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "workouts_exercises",
    foreignKeys = [
        ForeignKey(entity = Workout::class, parentColumns = ["id"], childColumns = ["workout_id_fk"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id_fk"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    ]
)
data class WorkoutExercise(
    //@ColumnInfo(name = "workout_exercise_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "workout_id_fk")
    val workoutId: Int,
    @ColumnInfo(name = "exercise_id_fk")
    val exerciseId: Int
)