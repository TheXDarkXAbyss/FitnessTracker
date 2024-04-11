package com.example.fitnesstracker.ui.exercise

import com.example.fitnesstracker.data.entity.Muscle
import com.example.fitnesstracker.data.dao.ExerciseDao
import com.example.fitnesstracker.data.dao.MuscleDao
import javax.inject.Inject

class ExerciseScreenRepository @Inject constructor(private val exerciseDao: ExerciseDao, private val muscleDao: MuscleDao){

    fun getAllMuscles() = muscleDao.getAllMuscles()

    fun getExercisesByMuscle(muscleId: Int) = exerciseDao.getExercisesByMuscleId(muscleId = muscleId)

    fun getMusclesWithNumberOfExercises() = muscleDao.getAllMusclesWithNumberOfExercises()

}