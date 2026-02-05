package com.example.fitnessprofront.features.recipies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessprofront.features.recipies.domain.usecases.CreateRecipeUseCase
import com.example.fitnessprofront.features.recipies.domain.usecases.DeleteRecipeUseCase
import com.example.fitnessprofront.features.recipies.domain.usecases.GetRecipiesUseCase
import com.example.fitnessprofront.features.recipies.domain.usecases.UpdateRecipeUseCase

class RecipiesViewModelFactory(
    private val getRecipiesUseCase: GetRecipiesUseCase,
    private val createRecipeUseCase: CreateRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipiesViewModel(
                getRecipiesUseCase,
                createRecipeUseCase,
                updateRecipeUseCase,
                deleteRecipeUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}