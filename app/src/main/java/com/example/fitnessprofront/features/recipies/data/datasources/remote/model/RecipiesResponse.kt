package com.example.fitnessprofront.features.recipies.data.datasources.remote.model

data class RecipiesResponse(
    val data: List<RecipieResponseDto>
)

data class RecipieResponseDto(
    val id : String,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val user_id: Int,
    val scheduled_date: String?
)