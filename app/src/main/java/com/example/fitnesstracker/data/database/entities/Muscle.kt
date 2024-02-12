package com.example.fitnesstracker.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscle")
data class Muscle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)