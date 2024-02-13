package com.example.fitnesstracker.data.database.entitie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "exercises",
    foreignKeys = [ForeignKey(entity = Muscle::class, parentColumns = ["id"], childColumns = ["muscle_id_fk"],
        onDelete = ForeignKey.NO_ACTION, onUpdate = ForeignKey.CASCADE)]
)
data class Exercise(
    //@ColumnInfo(name = "exercise_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    //@ColumnInfo(name = "exercise_name")
    val name: String,
    @ColumnInfo(name = "muscle_id_fk")
    val muscleId: Int
)
