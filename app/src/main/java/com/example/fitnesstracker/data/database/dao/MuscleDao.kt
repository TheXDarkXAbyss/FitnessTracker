package com.example.fitnesstracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.database.entitie.Muscle
import kotlinx.coroutines.flow.Flow

@Dao
interface MuscleDao {

    @Upsert
    suspend fun upsert(muscle: Muscle)

    @Delete
    suspend fun delete(muscle: Muscle)

    @Query("SELECT * FROM muscles")
    fun getAll(): Flow<List<Muscle>>

    @Query("SELECT * FROM muscles WHERE id = :id")
    fun getById(id: Int): Flow<Muscle>

}