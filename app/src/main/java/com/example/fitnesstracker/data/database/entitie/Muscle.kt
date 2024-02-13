package com.example.fitnesstracker.data.database.entitie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscles")
data class Muscle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)