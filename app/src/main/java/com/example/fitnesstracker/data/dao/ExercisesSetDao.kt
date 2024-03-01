package com.example.fitnesstracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.ExercisesSets

@Dao
interface ExercisesSetDao {

    @Query("""
        SELECT ES.id, ES.workout_exercise_id, ES.exercise_id, ES.session_id, ES.set_number, ES.reps, ES.weight, ES.difficulty FROM exercises_sets AS ES
        INNER JOIN workout_exercises AS WE ON WE.id = ES.workout_exercise_id
		WHERE WE.workout_id = :workoutId AND ES.session_id = :sessionId
    """)
    suspend fun getExercisesSetsByWorkoutIdAndSessionId(workoutId: Int, sessionId: Int): List<ExercisesSets>

    @Upsert
    suspend fun upsertExercisesSet(allWorkoutSets: List<ExercisesSets>)

}