package com.example.fitnessprofront.features.recipies.data.datasources.remote.mapper

import com.example.fitnessprofront.features.recipies.data.datasources.remote.model.RecipieResponseDto
import com.example.fitnessprofront.features.recipies.domain.entities.Recipe

fun RecipieResponseDto.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        name = this.name,
        ingredients = this.ingredients,
        instructions = this.instructions,
        userId = this.user_id,
        scheduledDate = this.scheduled_date
    )
}