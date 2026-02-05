package com.example.fitnessprofront.features.recipies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessprofront.features.recipies.domain.usecases.GetRecipiesUseCase

class RecipiesViewModelFactory(
    private val getRecipiesUseCase: GetRecipiesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipiesViewModel(getRecipiesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}