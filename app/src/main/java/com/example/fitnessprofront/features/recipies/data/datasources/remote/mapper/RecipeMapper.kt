// RecipeMapper.kt CORREGIDO
package com.example.fitnessprofront.features.recipies.data.datasources.remote.mapper

import com.example.fitnessprofront.features.recipies.data.datasources.remote.model.RecipeDto
import com.example.fitnessprofront.features.recipies.domain.entities.Recipe

fun RecipeDto.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        name = this.name ?: "Receta sin nombre",  // ← Maneja null
        description = this.description ?: "",      // ← Maneja null
        ingredients = this.ingredients ?: "",      // ← Maneja null
        instructions = this.instructions ?: "",    // ← Maneja null
        userId = this.userId,
        scheduledDays = this.scheduledDays,        // ← Ya es List<String>? en DTO
        mealType = this.mealType ?: ""             // ← Maneja null
    )
}