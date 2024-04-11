package com.example.fitnesstracker.ui.home

import com.example.fitnesstracker.data.dao.WorkoutDao
import com.example.fitnesstracker.data.dao.WorkoutExerciseDao
import com.example.fitnesstracker.data.entity.Workout
import com.example.fitnesstracker.data.entity.WorkoutExercises
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val workoutDao: WorkoutDao, private val workoutExercisesDao: WorkoutExerciseDao) {

    fun getAllWorkouts(): Flow<List<Workout>> = workoutDao.getAllWorkouts()

    suspend fun upsertWorkout(workout: Workout) = workoutDao.upsertWorkout(workout)
    suspend fun upsertWorkoutExercises(workoutExercises: List<WorkoutExercises>) =workoutExercisesDao.insertWorkoutExercises(workoutExercises = workoutExercises)

}