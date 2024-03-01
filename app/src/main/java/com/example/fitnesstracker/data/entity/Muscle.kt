package com.example.fitnesstracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*enum class MuscleList {
    Chest,
    Back,
    Leg,
    Shoulders,
    Biceps,
    Triceps,
    Non
}*/

@Entity(tableName = "muscles")
data class Muscle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "muscle_name")
    val name: String = ""
)