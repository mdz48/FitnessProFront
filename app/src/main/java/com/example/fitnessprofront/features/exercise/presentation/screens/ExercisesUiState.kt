package com.example.fitnessprofront.features.exercise.presentation.screens

import com.example.fitnessprofront.features.exercise.domain.entities.Exercise

data class ExercisesUiState(
    val isLoading: Boolean = false,
    val exercises: List<Exercise> = emptyList(),
    val error: String? = null,
    val isRefreshing: Boolean = false,
    val isFilterExpanded: Boolean = false,
    val selectedBodyPart: String? = null
)