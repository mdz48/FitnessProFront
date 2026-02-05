package com.example.fitnessprofront.features.recipies.domain.repositories

import com.example.fitnessprofront.features.recipies.domain.entities.Recipe

interface RecipeRepository {
    suspend fun getRecipies(): List<Recipe>
}