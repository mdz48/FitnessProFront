package com.example.fitnessprofront.features.recipies.di

import com.example.fitnessprofront.core.di.AppContainer
import com.example.fitnessprofront.features.recipies.domain.usecases.GetRecipiesUseCase
import com.example.fitnessprofront.features.recipies.presentation.viewmodels.RecipiesViewModelFactory

class RecipeModule(
    private val appContainer: AppContainer
) {
    fun provideGetRecipiesUseCase(): GetRecipiesUseCase {
        return GetRecipiesUseCase(appContainer.recipeRepository)
    }

    fun provideRecipiesViewModelFactory(): RecipiesViewModelFactory {
        return RecipiesViewModelFactory(
            provideGetRecipiesUseCase()
        )
    }
}