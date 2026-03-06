package com.example.fitnessprofront.features.exercise.domain.repositories
import com.example.fitnessprofront.features.exercise.domain.entities.Exercise


interface ExerciseRepository {
    suspend fun getExercises(): List<Exercise>
    suspend fun getExercisesByBodyPart(bodyPart: String): List<Exercise>
}