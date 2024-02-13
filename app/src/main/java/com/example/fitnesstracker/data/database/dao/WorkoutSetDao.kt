package com.example.fitnesstracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Upsert
import com.example.fitnesstracker.data.database.entitie.Session
import com.example.fitnesstracker.data.database.entitie.WorkoutExercise
import com.example.fitnesstracker.data.database.entitie.WorkoutSet
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSetDao {

    @Upsert
    suspend fun upsert(workoutSet: WorkoutSet)

    @Delete
    suspend fun delete(workoutSet: WorkoutSet)

    @Query("SELECT * FROM workouts_sets")
    fun getAll(): Flow<List<WorkoutSet>>

    @Query("SELECT * FROM workouts_sets WHERE id = :id")
    fun getById(id: Int): Flow<WorkoutSet>

    @Query("""
            SELECT * FROM workouts_sets AS WS
            INNER JOIN workouts_exercises AS WE ON WS.workout_exercise_id_fk = WE.id 
            INNER JOIN sessions AS S ON WS.session_id_fk = S.id
    """)
    fun getAllWithWorkoutExercisesAndSessions(): Flow<List<WorkoutSetWithWorkoutExerciseAndSession>>

    @Query("""
            SELECT * FROM workouts_sets AS WS
            INNER JOIN workouts_exercises AS WE ON WS.workout_exercise_id_fk = WE.id 
            INNER JOIN sessions AS S ON WS.session_id_fk = S.id
            WHERE WS.id = :id
    """)
    fun getByIdWithWorkoutExercisesAndSessions(id: Int): Flow<WorkoutSetWithWorkoutExerciseAndSession>

}

data class WorkoutSetWithWorkoutExerciseAndSession(
    @Embedded val workoutSet: WorkoutSet,
    @Relation(parentColumn = "workout_exercise_id_fk", entityColumn = "id") val workoutExercise: WorkoutExercise,
    @Relation(parentColumn = "session_id_fk", entityColumn = "id") val session: Session
)