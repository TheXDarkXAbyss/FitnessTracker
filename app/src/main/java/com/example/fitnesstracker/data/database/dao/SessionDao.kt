package com.example.fitnesstracker.data.database.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.fitnesstracker.data.database.entitie.Session
import kotlinx.coroutines.flow.Flow

interface SessionDao {

    @Upsert
    suspend fun upsert(session: Session)

    @Delete
    suspend fun delete(session: Session)

    @Query("SELECT * FROM sessions")
    fun getAll(): Flow<List<Session>>

    @Query("SELECT * FROM sessions WHERE id = :id")
    fun getById(id: Int): Flow<Session>

}