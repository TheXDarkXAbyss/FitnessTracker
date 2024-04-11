package com.example.fitnesstracker.ui.home.workout

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.dao.WorkoutExercise
import com.example.fitnesstracker.data.entity.Exercise
import com.example.fitnesstracker.data.entity.ExercisesSets
import com.example.fitnesstracker.data.entity.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class WorkoutScreenViewModel @Inject constructor(private val workoutScreenRepository: WorkoutScreenRepository, savedStateHandle: SavedStateHandle): ViewModel() {

    // workout id sent by the navigation
    val workoutId = savedStateHandle.get<Int>("workoutId") ?: 0
    private var workoutStartTime: LocalDateTime

    /* *** Data *** */

    val workoutExercises = workoutScreenRepository.getWorkoutExercises(workoutId = workoutId)
    private var previousWorkoutSets: List<ExercisesSets> = emptyList()
    private var newWorkoutSets: MutableList<ExercisesSets> = mutableListOf()
    init {
        getPreviousWorkoutSets()
        workoutStartTime = LocalDateTime.now()
        //Log.d("testing", workoutStartTime.toString())
    }

    // get all previous workout sets
    private fun getPreviousWorkoutSets() {
        viewModelScope.launch {
            val lastSessionId = workoutScreenRepository.getLatestSessionByWorkoutId(workoutId = workoutId).id
            previousWorkoutSets = workoutScreenRepository.getExercisesSetsByWorkoutIdAndSessionId(workoutId = workoutId, sessionId = lastSessionId)
        }
    }

    private fun setSession(sessionId: Long) {
        for (x in 0..<newWorkoutSets.count()) {
            newWorkoutSets[x] = newWorkoutSets[x].copy(sessionId = sessionId.toInt())
        }
    }




    /* *** Events *** */

    private var selectedExercise: WorkoutExercise = WorkoutExercise()

    // Set the selected exercise
    fun setSelectedExercise(workoutExercise: WorkoutExercise) {
        selectedExercise = workoutExercise
    }

    // Open the sets dialog
    fun openSetsDialog() {

        // populate the fields with the new sets data if such data exists
        newWorkoutSets.forEach { exercisesSets ->
            if (exercisesSets.exerciseId == selectedExercise.exercise.id) {
                addSetFields(reps = exercisesSets.reps, weight = exercisesSets.weight, difficulty = exercisesSets.difficulty)
            }
        }

        if (txtRepsValues.isNotEmpty()){
            isSetsDialogOpen.value = true
            return

        }

        // the code below will only execute one time
        // on the first time the user will click on an exercise

        val exerciseSets: MutableList<ExercisesSets> = mutableListOf()

        // search for the exercise sets between all the previous workout sets
        previousWorkoutSets.forEach {
            if (it.exerciseId == selectedExercise.exercise.id) {
                exerciseSets.add(it)
                // adding state for the set fields
                addSetFields() // empty
                setPreviousSetData(reps = it.reps, weight = it.weight, difficulty = it.difficulty) // from the previous workout
            }
        }

        // add three sets fields and their states if there was no previous sets
        if (exerciseSets.isEmpty()) {
            exerciseSets.add(ExercisesSets())
            addSetFields() // empty
            setPreviousSetData() // empty

            exerciseSets.add(ExercisesSets())
            addSetFields() // empty
            setPreviousSetData() // empty

            exerciseSets.add(ExercisesSets())
            addSetFields() // empty
            setPreviousSetData() // empty
        }

        isSetsDialogOpen.value = true
    }

    fun closeSetsDialog() {

        val newExerciseSets = getSetsDataFormUI()

        for (set in newExerciseSets.iterator()) {
            var addNew: Boolean = true
            var setIndex = -1
            newWorkoutSets.forEachIndexed { index, it ->
                if (it.workoutExerciseId == set.workoutExerciseId && it.setNumber == set.setNumber)
                {
                    addNew = false
                    Log.d("testing", addNew.toString())
                    setIndex = index
                    return@forEachIndexed
                }
                addNew = true
                Log.d("testing", addNew.toString())
            }

            if (addNew) newWorkoutSets.add(set)
            else newWorkoutSets[setIndex] = set
        }


        setSelectedExercise(workoutExercise = WorkoutExercise())
        isSetsDialogOpen.value = false
        clearAllFields()
    }

    fun save() {
        viewModelScope.launch {
            val sessionId = workoutScreenRepository.upsertSession(
                Session(
                    workoutId = workoutId,
                    date = Date(),
                    sessionLength = 0
                )
            )

            setSession(sessionId = sessionId)

            workoutScreenRepository.upsertExercisesSet(newWorkoutSets)
        }
    }

    /* *** UI *** */

    val txtRepsValues = mutableStateListOf<String>()
    val txtWeightValues = mutableStateListOf<String>()
    val btnSelectedButtonDifficulty = mutableStateListOf<Int>()
    val previousSetData = mutableStateListOf<String>()

    var isSetsDialogOpen = mutableStateOf(false)
        private set



    // Adds the state value variable for a text-field
    private fun addSetFields(reps:Int = 0, weight:Int = 0, difficulty: Int = 0) {
        txtRepsValues.add(if(reps == 0) "" else reps.toString())
        txtWeightValues.add(if(weight == 0) "" else weight.toString())
        btnSelectedButtonDifficulty.add(difficulty)
    }

    // update the state value for reps text-field
    fun updateTxtRepsValues(index:Int, newValue: String) {
        txtRepsValues[index] = newValue
    }

    // update the state value for weight text-field
    fun updateTxtWeightValues(index:Int, newValue: String) {
        txtWeightValues[index] = newValue
    }

    // update the state value for difficulty text-field
    fun updateBtnSelectedButtonDifficulty(index:Int, newValue: Int) {
        if (btnSelectedButtonDifficulty[index] != newValue)
            btnSelectedButtonDifficulty[index] = newValue
    }

    private fun clearAllFields() {
        txtRepsValues.clear()
        txtWeightValues.clear()
        btnSelectedButtonDifficulty.clear()
    }

    private fun setPreviousSetData(reps:Int = 0, weight:Int = 0, difficulty: Int = 0) {
        previousSetData.add("${reps}x${weight}/${difficulty}")
    }

    private fun getSetsDataFormUI(): List<ExercisesSets> {
        // collect the new/already entered data
        val newExerciseSets = mutableListOf<ExercisesSets>()

        for (x in 0..<txtRepsValues.count()) {
            newExerciseSets.add(
                ExercisesSets(
                    workoutExerciseId = selectedExercise.workoutExerciseId,
                    exerciseId = selectedExercise.exercise.id,
                    setNumber = x+1,
                    reps = if(txtRepsValues[x] != "") txtRepsValues[x].toInt() else 0,
                    weight = if(txtWeightValues[x] != "") txtWeightValues[x].toInt() else 0,
                    difficulty = btnSelectedButtonDifficulty[x]
                )
            )
        }

        return newExerciseSets
    }

}