package com.example.fitnessprofront.features.recipies.domain.repositories

import com.example.fitnessprofront.features.recipies.domain.entities.Recipe

interface RecipeRepository {
    suspend fun getRecipies(): List<Recipe>

    suspend fun createRecipe(
        name: String,
        description: String,
        ingredients: String,
        instructions: String,
        userId: Int?,
        scheduledDays: List<String>,
        mealType: String
    ): Recipe

    suspend fun updateRecipe(
        recipeId: Int,
        name: String,
        description: String,
        ingredients: String,
        instructions: String,
        userId: Int?,
        scheduledDays: List<String>,
        mealType: String
    ): Recipe

    suspend fun deleteRecipe(recipeId: Int)
}