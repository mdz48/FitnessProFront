package com.example.fitnessprofront.features.recipies.domain.usecases

import com.example.fitnessprofront.features.recipies.domain.entities.Recipe
import com.example.fitnessprofront.features.recipies.domain.repositories.RecipeRepository

class UpdateRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(
        recipeId: Int,
        name: String,
        description: String,
        ingredients: String,
        instructions: String,
        userId: Int?,
        scheduledDays: List<String>,
        mealType: String
    ): Recipe {
        return recipeRepository.updateRecipe(
            recipeId = recipeId,
            name = name,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            userId = userId,
            scheduledDays = scheduledDays,
            mealType = mealType
        )
    }
}
