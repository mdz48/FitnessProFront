// RecipeDto.kt - Actualiza para que coincida con la API
package com.example.fitnessprofront.features.recipies.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class RecipiesResponse(
    val data: List<RecipeDto>
)

data class RecipeDto(
    val id: Int,
    val name: String?,  // ← Ahora nullable
    val description: String?,  // ← Ahora nullable
    val ingredients: String?,  // ← Ahora nullable
    val instructions: String?,  // ← Ahora nullable
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("scheduled_days")
    val scheduledDays: List<String>?,  // ← Ya es nullable
    @SerializedName("meal_type")
    val mealType: String?  // ← Ahora nullable
)

data class RecipeCreateDto(
    val name: String,
    val description: String,
    val ingredients: String,
    val instructions: String,
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("scheduled_days")
    val scheduledDays: List<String>,
    @SerializedName("meal_type")
    val mealType: String
)