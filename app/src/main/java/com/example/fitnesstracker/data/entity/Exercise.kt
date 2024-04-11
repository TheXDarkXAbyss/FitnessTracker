package com.example.fitnesstracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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



