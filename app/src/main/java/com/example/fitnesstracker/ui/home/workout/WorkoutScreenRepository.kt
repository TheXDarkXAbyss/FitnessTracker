package com.example.fitnesstracker.ui.home.workout

import com.example.fitnesstracker.data.dao.SessionDao
import com.example.fitnesstracker.data.dao.WorkoutExerciseDao
import com.example.fitnesstracker.data.dao.ExercisesSetDao
import com.example.fitnesstracker.data.entity.ExercisesSets
import com.example.fitnesstracker.data.entity.Session
import javax.inject.Inject

class WorkoutScreenRepository @Inject constructor(private val workoutExerciseDao: WorkoutExerciseDao, private val setDao: ExercisesSetDao, private val sessionDao: SessionDao) {

    fun getWorkoutExercises(workoutId: Int) = workoutExerciseDao.getWorkoutExercisesByWorkoutId(workoutId = workoutId)

    suspend fun getLatestSessionByWorkoutId(workoutId: Int) = sessionDao.getLatestSessionByWorkoutId(workoutId = workoutId)

    suspend fun getExercisesSetsByWorkoutIdAndSessionId(workoutId: Int, sessionId: Int) = setDao.getExercisesSetsByWorkoutIdAndSessionId(workoutId = workoutId, sessionId = sessionId)

    suspend fun upsertExercisesSet(allWorkoutSets: List<ExercisesSets>) = setDao.upsertExercisesSet(allWorkoutSets = allWorkoutSets)

    suspend fun upsertSession(session: Session): Long = sessionDao.upsertSession(session = session)
}