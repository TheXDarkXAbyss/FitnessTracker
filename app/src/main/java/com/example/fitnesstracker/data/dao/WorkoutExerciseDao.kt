package com.example.fitnesstracker.data.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.WorkoutExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExerciseDao {

    @Insert
    suspend fun insertWorkoutExercises(workoutExercises: List<WorkoutExercises>)

    @Query("""
        SELECT WE.id as workoutExerciseId, E.id, E.exercise_name, E.targeted_muscle_id FROM workout_exercises AS WE
        INNER JOIN exercises AS E ON E.id = WE.exercise_id
        WHERE WE.workout_id = :workoutId
    """)
    fun getWorkoutExercisesByWorkoutId(workoutId: Int) : Flow<List<WorkoutExercise>>

}

data class WorkoutExercise(
    val workoutExerciseId: Int = 0,
    @Embedded val exercise: Exercise = Exercise()
)