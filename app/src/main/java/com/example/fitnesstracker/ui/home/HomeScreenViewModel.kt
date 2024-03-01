package com.example.fitnesstracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.Workout
import com.example.fitnesstracker.data.entity.WorkoutExercises
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val homeRepository: HomeRepository): ViewModel() {

    fun getAllWorkouts() = homeRepository.getAllWorkouts()


}