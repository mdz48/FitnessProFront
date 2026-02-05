package com.example.fitnessprofront.features.recipies.domain.usecases

import com.example.fitnessprofront.features.recipies.domain.entities.Recipe
import com.example.fitnessprofront.features.recipies.domain.repositories.RecipeRepository

class GetRecipiesUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(): List<Recipe> {
        return recipeRepository.getRecipies()
    }
}