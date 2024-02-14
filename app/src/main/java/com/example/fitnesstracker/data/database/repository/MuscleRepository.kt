package com.example.fitnesstracker.data.database.repository

import com.example.fitnesstracker.data.database.entitie.Muscle
import kotlinx.coroutines.flow.Flow

interface MuscleRepository {

    fun getAllMusclesStream(): Flow<List<Muscle>>

    fun getMuscleByIdStream(muscleId: Int): Flow<Muscle>

    suspend fun upsert(muscle: Muscle)

    suspend fun delete(muscle: Muscle)

}