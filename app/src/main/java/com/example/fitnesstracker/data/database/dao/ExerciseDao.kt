package com.example.fitnesstracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.database.entitie.Exercise
import com.example.fitnesstracker.data.database.entitie.Muscle
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Upsert
    suspend fun upsert(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Query("SELECT * FROM exercises")
    fun getAll(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun getById(id: Int): Flow<Exercise>

    @Query("""
        SELECT * FROM exercises AS E INNER JOIN muscles AS M
        ON E.targeted_muscle_id = M.id
    """)
    fun getAllWithMuscle(): Flow<List<ExerciseWithMuscle>>

    @Query("""
        SELECT * FROM exercises AS E INNER JOIN muscles AS M
        ON E.targeted_muscle_id = M.id
        WHERE E.id = :id
    """)
    fun getByIdWithMuscle(id: Int): Flow<ExerciseWithMuscle>
}

data class ExerciseWithMuscle(
    val exercise: Exercise,
    val muscle: Muscle
)
