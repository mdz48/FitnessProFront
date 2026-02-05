package com.example.fitnessprofront.features.recipies.data.datasources.remote.mapper

import com.example.fitnessprofront.features.recipies.data.datasources.remote.model.RecipeDto
import com.example.fitnessprofront.features.recipies.domain.entities.Recipe

fun RecipeDto.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        name = this.name,
        description = this.description,
        ingredients = this.ingredients,
        instructions = this.instructions,
        userId = this.userId,
        scheduledDatetime = this.scheduledDatetime
    )
}