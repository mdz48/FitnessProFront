package com.example.fitnessprofront.features.recipies.domain.entities

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val ingredients: String?,
    val instructions: String?,
    val userId: Int?,

    val scheduledDays: List<String>?,
    val mealType: String
) {

    fun scheduledDaysOrEmpty(): List<String> = scheduledDays ?: emptyList()
}