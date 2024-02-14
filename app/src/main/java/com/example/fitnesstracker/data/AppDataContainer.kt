package com.example.fitnesstracker.data

import android.content.Context
import com.example.fitnesstracker.data.database.FitnessTrackerDatabase
import com.example.fitnesstracker.data.database.repository.MuscleRepository
import com.example.fitnesstracker.data.database.repository.OfflineMuscleRepository

interface AppContainer {
    val muscleRepository: MuscleRepository
}
class AppDataContainer(private val context: Context) : AppContainer {

    override val muscleRepository: MuscleRepository by lazy {
        OfflineMuscleRepository(FitnessTrackerDatabase.getDatabase(context).muscleDao())
    }
}
