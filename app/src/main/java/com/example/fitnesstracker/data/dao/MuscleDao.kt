package com.example.fitnesstracker.data.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import com.example.fitnesstracker.data.entity.Muscle
import kotlinx.coroutines.flow.Flow

@Dao
interface MuscleDao {

    @Query("SELECT * FROM muscles")
    fun getAllMuscles(): Flow<List<Muscle>>

    @Query("""
        SELECT M.*, count(targeted_muscle_id) AS numberOfExercises FROM muscles AS M
        LEFT JOIN exercises AS E ON M.id = E.targeted_muscle_id
        GROUP BY muscle_name
		ORDER By M.id
    """)
    fun getAllMusclesWithNumberOfExercises(): Flow<List<MusclesWithNumberOfExercises>>

}

data class MusclesWithNumberOfExercises(
    @Embedded val muscle: Muscle = Muscle(),
    val numberOfExercises: Int = 0
)