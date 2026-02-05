package com.example.fitnessprofront.features.recipies.domain.usecases

import com.example.fitnessprofront.features.recipies.domain.entities.Recipe
import com.example.fitnessprofront.features.recipies.domain.repositories.RecipeRepository

class CreateRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(
        name: String,
        description: String,
        ingredients: String,
        instructions: String,
        userId: Int? = null,
        scheduledDatetime: String? = null
    ): Recipe {
        return recipeRepository.createRecipe(
            name = name,
            description = description,
            ingredients = ingredients,
            instructions = instructions,
            userId = userId,
            scheduledDatetime = scheduledDatetime
        )
    }
}
