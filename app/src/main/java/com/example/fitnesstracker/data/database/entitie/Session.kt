package com.example.fitnesstracker.data.database.entitie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class Session(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long,
    val time: Long,
    val length: Float
)
