package com.example.fitnesstracker.data.database.repository

import com.example.fitnesstracker.data.database.dao.ExerciseDao
import com.example.fitnesstracker.data.database.entitie.Exercise
import kotlinx.coroutines.flow.Flow

class OfflineExerciseRepository(private val exerciseDao: ExerciseDao) : ExerciseRepository {
    override fun getAllExercisesStream(): Flow<List<Exercise>> {
        return exerciseDao.getAll()
    }

    override fun getExerciseByIdStream(exerciseId: Int): Flow<Exercise> {
        return exerciseDao.getById(exerciseId)
    }

    override suspend fun upsertExercise(exercise: Exercise) {
        exerciseDao.upsert(exercise)
    }

    override suspend fun delete(exercise: Exercise) {
        exerciseDao.delete(exercise)
    }


}