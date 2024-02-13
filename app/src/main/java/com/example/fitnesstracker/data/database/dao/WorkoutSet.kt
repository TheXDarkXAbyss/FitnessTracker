package com.example.fitnesstracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.database.entitie.Session
import com.example.fitnesstracker.data.database.entitie.WorkoutExercise
import com.example.fitnesstracker.data.database.entitie.WorkoutSet
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSet {

    @Upsert
    suspend fun upsert(workoutSet: WorkoutSet)

    @Delete
    suspend fun delete(workoutSet: WorkoutSet)

    @Query("SELECT * FROM workouts_sets")
    fun getAll(): Flow<List<WorkoutExercise>>

    @Query("SELECT * FROM workouts_sets WHERE id = :id")
    fun getById(id: Int): Flow<WorkoutExercise>

    @Query("""
            SELECT * FROM workouts_sets AS WS
            INNER JOIN workouts_exercises AS WE ON WS.workout_exercise_id = WE.id 
            INNER JOIN sessions AS S ON WS.session_id = S.id
    """)
    fun getAllWithWorkoutExercisesAndSessions(): Flow<List<WorkoutSetWithWorkoutExerciseAndSession>>

    @Query("""
            SELECT * FROM workouts_sets AS WS
            INNER JOIN workouts_exercises AS WE ON WS.workout_exercise_id = WE.id 
            INNER JOIN sessions AS S ON WS.session_id = S.id
            WHERE WS.id = :id
    """)
    fun getByIdWithWorkoutExercisesAndSessions(id: Int): Flow<WorkoutSetWithWorkoutExerciseAndSession>

}

data class WorkoutSetWithWorkoutExerciseAndSession(
    val workoutSet: WorkoutSet,
    val workoutExercise: WorkoutExercise,
    val session: Session
)