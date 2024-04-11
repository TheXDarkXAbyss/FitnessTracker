package com.example.fitnesstracker.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.entity.Session

@Dao
interface SessionDao {

    @Query("SELECT id, workout_id, max(date) as date, session_length FROM sessions WHERE workout_id = :workoutId")
    suspend fun getLatestSessionByWorkoutId(workoutId: Int): Session

    @Upsert
    suspend fun upsertSession(session: Session): Long

}