package com.example.fitnesstracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.Muscle
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Upsert
    suspend fun upsertExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Query("SELECT * FROM exercises WHERE targeted_muscle_id = :muscleId")
    fun getExercisesByMuscleId(muscleId: Int): Flow<List<Exercise>>

}