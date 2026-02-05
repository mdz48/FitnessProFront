package com.example.fitnessprofront.features.recipies.domain.entities

data class Recipe(
    val id: String,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val userId: Int,
    val scheduledDate: String?
)