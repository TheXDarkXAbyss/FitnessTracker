package com.example.fitnesstracker.data


private val exerciseList: List<Exercise> = listOf(
    // Chest Exercises
    Exercise(name = "Barbell Bench Press", targetedMuscle = Muscle.Chest),
    Exercise(name = "Dumbbell Bench Press", targetedMuscle = Muscle.Chest),
    Exercise(name = "Dumbbell Chest Fly", targetedMuscle = Muscle.Chest),
    Exercise(name = "Machine Chest Fly", targetedMuscle = Muscle.Chest),
    Exercise(name = "Cable Crossover", targetedMuscle = Muscle.Chest),
    Exercise(name = "Machine Chest Press", targetedMuscle = Muscle.Chest),
    Exercise(name = "Push Ups", targetedMuscle = Muscle.Chest),

    // Back Exercises
    Exercise(name = "Deadlift", targetedMuscle = Muscle.Back),
    Exercise(name = "Bent-Over Row", targetedMuscle = Muscle.Back),
    Exercise(name = "Pull Ups", targetedMuscle = Muscle.Back),
    Exercise(name = "T-Bar Row", targetedMuscle = Muscle.Back),
    Exercise(name = "Seated Row", targetedMuscle = Muscle.Back),
    Exercise(name = "Single-Arm Smith Machine Row", targetedMuscle = Muscle.Back),
    Exercise(name = "Lat Pull-Down", targetedMuscle = Muscle.Back),
    Exercise(name = "Single Arm Dumbbell Row", targetedMuscle = Muscle.Back),
    Exercise(name = "Dumbbell Pull-Over", targetedMuscle = Muscle.Back),
    Exercise(name = "Chest Supported Row", targetedMuscle = Muscle.Back),

)

fun getNumberOfExercisesByMuscle(muscle: Muscle): Int {
    val exerciseByMuscleList: MutableList<Exercise> = mutableListOf()

    exerciseList.forEach {
        if (it.targetedMuscle == muscle) {
            exerciseByMuscleList.add(it)
        }
    }

    return exerciseByMuscleList.size
}

fun getExercisesByMuscle(muscle: Muscle?): List<Exercise> {
    val exerciseByMuscleList: MutableList<Exercise> = mutableListOf()

    exerciseList.forEach {
        if (it.targetedMuscle == muscle) {
            exerciseByMuscleList.add(it)
        }
    }

    return exerciseByMuscleList
}

data class Exercise(
    val name: String,
    val targetedMuscle: Muscle
) {

    fun getAllExercises(): List<Exercise> {
        return exerciseList
    }

}


