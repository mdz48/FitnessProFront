package com.example.fitnessprofront.features.user.presentation.screens

data class RegisterUIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isRegistered: Boolean = false
)
