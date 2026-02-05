package com.example.fitnessprofront.features.recipies.data.repositories

import com.example.fitnessprofront.core.network.FitnessProApi
import com.example.fitnessprofront.features.recipies.data.datasources.remote.mapper.toDomain
import com.example.fitnessprofront.features.recipies.domain.entities.Recipe
import com.example.fitnessprofront.features.recipies.domain.repositories.RecipeRepository

class RecipeRepositoryImp(
    private val fitnessProApi: FitnessProApi
) : RecipeRepository {
    override suspend fun getRecipies(): List<Recipe> {
        return fitnessProApi.getRecipies().map { it.toDomain() }
    }
}