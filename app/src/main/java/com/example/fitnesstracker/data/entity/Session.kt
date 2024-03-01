package com.example.fitnesstracker.data.entity

import android.health.connect.datatypes.units.Length
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "sessions",
    foreignKeys = [
        ForeignKey(entity = Workout::class, parentColumns = ["id"], childColumns = ["workout_id"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.SET_DEFAULT)
    ]
)
data class Session(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "workout_id")
    val workoutId: Int = 0,
    @ColumnInfo(name = "date")
    val date: Date = Date(),
    @ColumnInfo(name = "session_length")
    val sessionLength: Int
)
