package com.example.fitnesstracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnesstracker.data.database.dao.ExerciseDao
import com.example.fitnesstracker.data.database.dao.MuscleDao
import com.example.fitnesstracker.data.database.dao.SessionDao
import com.example.fitnesstracker.data.database.dao.WorkoutDao
import com.example.fitnesstracker.data.database.dao.WorkoutExerciseDao
import com.example.fitnesstracker.data.database.dao.WorkoutSetDao
import com.example.fitnesstracker.data.database.entitie.Exercise
import com.example.fitnesstracker.data.database.entitie.Muscle
import com.example.fitnesstracker.data.database.entitie.Session
import com.example.fitnesstracker.data.database.entitie.Workout
import com.example.fitnesstracker.data.database.entitie.WorkoutExercise
import com.example.fitnesstracker.data.database.entitie.WorkoutSet

@Database(
    entities = [Muscle::class, Workout::class, Session::class, Exercise::class, WorkoutExercise::class, WorkoutSet::class],
    version = 1,
    exportSchema = false
)
abstract class FitnessTrackerDatabase : RoomDatabase() {

    /* Add your Dao classes here
    *  example: abstract fun itemDao() : ItemDao
    */

    abstract fun muscleDao(): MuscleDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun session(): SessionDao
    abstract fun exercise(): ExerciseDao
    abstract fun workoutExercise(): WorkoutExerciseDao
    abstract fun workoutSet(): WorkoutSetDao

    companion object {
        @Volatile
        private var Instance: FitnessTrackerDatabase? = null

        fun getDatabase(context: Context) : FitnessTrackerDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FitnessTrackerDatabase::class.java, "fitness_tracker_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}