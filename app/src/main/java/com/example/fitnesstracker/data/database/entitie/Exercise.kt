package com.example.fitnesstracker.data.database.entitie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "exercises",
    foreignKeys = [ForeignKey(entity = Muscle::class, parentColumns = ["id"], childColumns = ["targetedMuscleId"],
        onDelete = ForeignKey.NO_ACTION, onUpdate = ForeignKey.CASCADE)]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "targeted_muscle_id")
    val targetedMuscleId: Int
)
