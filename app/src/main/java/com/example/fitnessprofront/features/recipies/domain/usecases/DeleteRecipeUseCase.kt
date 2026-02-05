package com.example.fitnessprofront.features.recipies.domain.usecases

import com.example.fitnessprofront.features.recipies.domain.repositories.RecipeRepository

class DeleteRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Int) {
        recipeRepository.deleteRecipe(recipeId)
    }
}
