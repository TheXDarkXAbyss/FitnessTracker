package com.example.fitnesstracker.data.database

import com.example.fitnesstracker.data.database.dao.ExerciseDao
import com.example.fitnesstracker.data.database.dao.MuscleDao
import com.example.fitnesstracker.data.database.dao.WorkoutDao
import com.example.fitnesstracker.data.database.dao.WorkoutExerciseDao

class Repository(
    private val muscleDao: MuscleDao,
    private val workoutDao: WorkoutDao,
    private val exerciseDao: ExerciseDao,
    private val sessionDao: ExerciseDao,
    private val workoutExerciseDao: WorkoutExerciseDao,
    private val workoutSetDao: WorkoutDao,
) {



}