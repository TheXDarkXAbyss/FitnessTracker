package com.example.fitnesstracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fitnesstracker.data.converters.RoomConverters
import com.example.fitnesstracker.data.dao.ExerciseDao
import com.example.fitnesstracker.data.dao.MuscleDao
import com.example.fitnesstracker.data.dao.SessionDao
import com.example.fitnesstracker.data.dao.WorkoutDao
import com.example.fitnesstracker.data.dao.WorkoutExerciseDao
import com.example.fitnesstracker.data.dao.ExercisesSetDao
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.ExercisesSets
import com.example.fitnesstracker.data.entity.Muscle
import com.example.fitnesstracker.data.entity.Session
import com.example.fitnesstracker.data.entity.Workout
import com.example.fitnesstracker.data.entity.WorkoutExercises

@Database(
    entities = [
        /* Add your entities here, example: Item::Class*/
        Muscle::class,
        Exercise::class,
        Workout::class,
        Session::class,
        WorkoutExercises::class,
        ExercisesSets::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class FitnessTrackerDatabase: RoomDatabase() {

    /* Add your Dao classes here
    *  example: abstract fun itemDao() : ItemDao
    */

    abstract fun muscleDao(): MuscleDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun sessionDao(): SessionDao
    abstract fun workoutExercisesDao(): WorkoutExerciseDao
    abstract fun workoutSetDao(): ExercisesSetDao

    companion object {
        @Volatile
        private var Instance: FitnessTrackerDatabase? = null

        fun getDatabase(context: Context) : FitnessTrackerDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FitnessTrackerDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/fitness_tracker_db.db")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}