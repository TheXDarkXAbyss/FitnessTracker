package com.example.fitnesstracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.database.entitie.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Upsert
    suspend fun upsert(workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)

    @Query("SELECT * FROM workouts")
    fun getAll(): Flow<List<Workout>>

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getById(id: Int): Flow<Workout>

}