package com.example.fitnesstracker.data.database.repository

import com.example.fitnesstracker.data.database.dao.MuscleDao
import com.example.fitnesstracker.data.database.entitie.Muscle
import kotlinx.coroutines.flow.Flow

class OfflineMuscleRepository(private val muscleDao: MuscleDao): MuscleRepository {
    override fun getAllMusclesStream(): Flow<List<Muscle>> {
        return muscleDao.getAll()
    }

    override fun getMuscleByIdStream(muscleId: Int): Flow<Muscle> {
        return muscleDao.getById(muscleId)
    }

    override suspend fun upsert(muscle: Muscle) {
        muscleDao.upsert(muscle)
    }

    override suspend fun delete(muscle: Muscle) {
        muscleDao.delete(muscle)
    }
}