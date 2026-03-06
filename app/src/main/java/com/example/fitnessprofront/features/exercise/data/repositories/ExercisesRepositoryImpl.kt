package com.example.fitnessprofront.features.exercise.data.repositories

import com.example.fitnessprofront.core.network.FitnessProApi
import com.example.fitnessprofront.features.exercise.data.datasources.remote.mapper.toDomain
import com.example.fitnessprofront.features.exercise.domain.entities.Exercise
import com.example.fitnessprofront.features.exercise.domain.repositories.ExerciseRepository

class ExercisesRepositoryImpl(
    private val api: FitnessProApi
) : ExerciseRepository {

    override suspend fun getExercises(): List<Exercise> {
        val response = api.getExercises(
            limit = 25
        )
        return response.data.map { it.toDomain() }
    }

    override suspend fun getExercisesByBodyPart(bodyPart: String): List<Exercise> {
        val response = api.getExercisesByBodyPart(
            limit = 25,
            bodyPart = bodyPart
        )
        return response.data.map { it.toDomain() }
    }
}