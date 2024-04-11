package com.example.fitnesstracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.entity.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workouts")
    fun  getAllWorkouts(): Flow<List<Workout>>

    @Upsert
    suspend fun upsertWorkout(workout: Workout) : Long

    @Query("SELECT last_insert_rowid()")
    suspend fun getLastInsertedId(): Int

}