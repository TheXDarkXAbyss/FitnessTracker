package com.example.fitnesstracker.data.database.entitie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    //@ColumnInfo(name = "workout_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val deleted: Boolean = false
)
