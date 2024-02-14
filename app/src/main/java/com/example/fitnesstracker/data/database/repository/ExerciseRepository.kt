package com.example.fitnesstracker.data.database.repository

import com.example.fitnesstracker.data.database.entitie.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    fun getAllExercisesStream(): Flow<List<Exercise>>

    fun getExerciseByIdStream(exerciseId: Int): Flow<Exercise>

    suspend fun upsertExercise(exercise: Exercise)

    suspend fun delete(exercise: Exercise)

}