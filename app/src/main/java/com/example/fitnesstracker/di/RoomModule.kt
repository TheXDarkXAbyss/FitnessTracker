package com.example.fitnesstracker.di

import android.content.Context
import com.example.fitnesstracker.data.FitnessTrackerDatabase
import com.example.fitnesstracker.data.dao.ExerciseDao
import com.example.fitnesstracker.data.dao.MuscleDao
import com.example.fitnesstracker.data.dao.SessionDao
import com.example.fitnesstracker.data.dao.WorkoutDao
import com.example.fitnesstracker.data.dao.WorkoutExerciseDao
import com.example.fitnesstracker.data.dao.ExercisesSetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideFitnessTrackerDatabase(@ApplicationContext context: Context): FitnessTrackerDatabase {
        return FitnessTrackerDatabase.getDatabase(context)
    }

    @Provides
    fun provideMuscleDao(fitnessTrackerDatabase: FitnessTrackerDatabase): MuscleDao {
        return fitnessTrackerDatabase.muscleDao()
    }

    @Provides
    fun provideExerciseDao(fitnessTrackerDatabase: FitnessTrackerDatabase): ExerciseDao {
        return fitnessTrackerDatabase.exerciseDao()
    }

    @Provides
    fun provideHomeDao(fitnessTrackerDatabase: FitnessTrackerDatabase): WorkoutDao {
        return fitnessTrackerDatabase.workoutDao()
    }

    @Provides
    fun provideWorkoutExercisesDao(fitnessTrackerDatabase: FitnessTrackerDatabase): WorkoutExerciseDao {
        return fitnessTrackerDatabase.workoutExercisesDao()
    }

    @Provides
    fun provideWorkoutSetDao(fitnessTrackerDatabase: FitnessTrackerDatabase): ExercisesSetDao {
        return fitnessTrackerDatabase.workoutSetDao()
    }

    @Provides
    fun provideSessionDao(fitnessTrackerDatabase: FitnessTrackerDatabase): SessionDao {
        return fitnessTrackerDatabase.sessionDao()
    }

}