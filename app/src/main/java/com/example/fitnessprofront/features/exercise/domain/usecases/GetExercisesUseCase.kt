package com.example.fitnessprofront.features.exercise.domain.usecases

import com.example.fitnessprofront.features.exercise.domain.repositories.ExerciseRepository
import com.example.fitnessprofront.features.exercise.domain.entities.Exercise

class GetExercisesUseCase(
    private val repository: ExerciseRepository
) {

    suspend operator fun invoke(): Result<List<Exercise>> {
        return try {
            val exercises = repository.getExercises()
            val filteredExercises = exercises.filter { it.name.isNotBlank() }

            if (filteredExercises.isEmpty()) {
                Result.failure(Exception("No se encontraron ejercicios válidos"))
            } else {
                Result.success(filteredExercises)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}