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
        scheduledDays: List<String>,
        mealType: String
    ): Recipe {
        val recipeCreateDto = RecipeCreateDto(
            name = name,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            userId = userId,
            scheduledDays = scheduledDays,
            mealType = mealType
        )
        return fitnessProApi.createRecipe(recipeCreateDto).toDomain()
    }

    override suspend fun updateRecipe(
        recipeId: Int,
        name: String,
        description: String,
        ingredients: String,
        instructions: String,
        userId: Int?,
        scheduledDays: List<String>,
        mealType: String
    ): Recipe {
        val recipeCreateDto = RecipeCreateDto(
            name = name,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            userId = userId,
            scheduledDays = scheduledDays,
            mealType = mealType
        )
        return fitnessProApi.updateRecipe(recipeId, recipeCreateDto).toDomain()
    }

    override suspend fun deleteRecipe(recipeId: Int) {
        fitnessProApi.deleteRecipe(recipeId)
    }
}