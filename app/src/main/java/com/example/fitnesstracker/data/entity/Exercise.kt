package com.example.fitnesstracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/*private val exerciseList: List<Exercise> = listOf(
    // Chest Exercises
    Exercise(name = "Barbell Bench Press", targetedMuscleId = 1),
    Exercise(name = "Dumbbell Bench Press", targetedMuscleId = Muscle.Chest),
    Exercise(name = "Dumbbell Chest Fly", targetedMuscleId = Muscle.Chest),
    Exercise(name = "Machine Chest Fly", targetedMuscleId = Muscle.Chest),
    Exercise(name = "Cable Crossover", targetedMuscleId = Muscle.Chest),
    Exercise(name = "Machine Chest Press", targetedMuscleId = Muscle.Chest),
    Exercise(name = "Push Ups", targetedMuscleId = Muscle.Chest),

    // Back Exercises
    Exercise(name = "Deadlift", targetedMuscleId = Muscle.Back),
    Exercise(name = "Bent-Over Row", targetedMuscleId = Muscle.Back),
    Exercise(name = "Pull Ups", targetedMuscleId = Muscle.Back),
    Exercise(name = "T-Bar Row", targetedMuscleId = Muscle.Back),
    Exercise(name = "Seated Row", targetedMuscleId = Muscle.Back),
    Exercise(name = "Single-Arm Smith Machine Row", targetedMuscleId = Muscle.Back),
    Exercise(name = "Lat Pull-Down", targetedMuscleId = Muscle.Back),
    Exercise(name = "Single Arm Dumbbell Row", targetedMuscleId = Muscle.Back),
    Exercise(name = "Dumbbell Pull-Over", targetedMuscleId = Muscle.Back),
    Exercise(name = "Chest Supported Row", targetedMuscleId = Muscle.Back),

)*/

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(entity = Muscle::class, parentColumns = ["id"], childColumns = ["targeted_muscle_id"],
            onDelete = ForeignKey.NO_ACTION, onUpdate = ForeignKey.CASCADE)
    ]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "exercise_name")
    val name: String = "",
    @ColumnInfo(name = "targeted_muscle_id")
    val targetedMuscleId: Int = 0
)



