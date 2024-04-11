package com.example.fitnesstracker.ui.home.createworkout

import com.example.fitnesstracker.data.dao.WorkoutDao
import com.example.fitnesstracker.data.dao.WorkoutExerciseDao
import com.example.fitnesstracker.data.entity.Workout
import com.example.fitnesstracker.data.entity.WorkoutExercises
import javax.inject.Inject

class CreateWorkoutRepository @Inject constructor(private val workoutExerciseDao: WorkoutExerciseDao, private val workoutDao: WorkoutDao) {

    suspend fun insertWorkout(workout: Workout) = workoutDao.upsertWorkout(workout = workout)

    suspend fun insertWorkoutExercises(workoutExercisesList: List<WorkoutExercises>) = workoutExerciseDao.insertWorkoutExercises(workoutExercises = workoutExercisesList)

    //suspend fun getLastInsertedWorkoutId() = workoutDao.getLastInsertedId()

}