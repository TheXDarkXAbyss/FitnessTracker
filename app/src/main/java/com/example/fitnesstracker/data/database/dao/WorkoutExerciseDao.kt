package com.example.fitnesstracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.database.entitie.Workout
import com.example.fitnesstracker.data.database.entitie.WorkoutExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExerciseDao {

    @Upsert
    suspend fun upsert(workoutExercise: WorkoutExercise)

    @Delete
    suspend fun delete(workoutExercise: WorkoutExercise)

    @Query("SELECT * FROM exercises")
    fun getAll(): Flow<List<WorkoutExercise>>

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun getById(id: Int): Flow<WorkoutExercise>

    @Query("""
            SELECT * FROM workouts_exercises AS WE
            INNER JOIN workouts AS W ON WE.workout_id = W.id 
            INNER JOIN exercises AS E ON WE.exercise_id = E.id
    """)
    fun getAllWithWorkoutAndExercise(): Flow<List<WorkoutExerciseWithWorkoutAndExercise>>

    @Query("""
            SELECT * FROM workouts_exercises AS WE
            INNER JOIN workouts AS W ON WE.workout_id = W.id 
            INNER JOIN exercises AS E ON WE.exercise_id = E.id
            WHERE WE.id = :id
    """)
    fun getByIdWithWorkoutAndExercise(id: Int): Flow<WorkoutExerciseWithWorkoutAndExercise>

}

data class WorkoutExerciseWithWorkoutAndExercise(
    val workoutExercise: WorkoutExercise,
    val workout: Workout,
    val exercise: WorkoutExercise
)
