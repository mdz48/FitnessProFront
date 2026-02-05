package com.example.fitnessprofront.features.recipies.data.repositories

import com.example.fitnessprofront.core.network.FitnessProApi
import com.example.fitnessprofront.features.recipies.data.datasources.remote.mapper.toDomain
import com.example.fitnessprofront.features.recipies.data.datasources.remote.model.RecipeCreateDto
import com.example.fitnessprofront.features.recipies.domain.entities.Recipe
import com.example.fitnessprofront.features.recipies.domain.repositories.RecipeRepository

class RecipeRepositoryImp(
    private val fitnessProApi: FitnessProApi
) : RecipeRepository {
    override suspend fun getRecipies(): List<Recipe> {
        return fitnessProApi.getRecipies().map { it.toDomain() }
    }

    override suspend fun createRecipe(
        name: String,
        description: String,
        ingredients: String,
        instructions: String,
        userId: Int?,
        scheduledDatetime: String?
    ): Recipe {
        val recipeCreateDto = RecipeCreateDto(
            name = name,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            userId = userId,
            scheduledDatetime = scheduledDatetime
        )
        return fitnessProApi.createRecipe(recipeCreateDto).toDomain()
    }
}