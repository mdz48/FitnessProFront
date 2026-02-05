package com.example.fitnessprofront.features.recipies.di

import com.example.fitnessprofront.core.di.AppContainer
import com.example.fitnessprofront.features.recipies.domain.usecases.CreateRecipeUseCase
import com.example.fitnessprofront.features.recipies.domain.usecases.DeleteRecipeUseCase
import com.example.fitnessprofront.features.recipies.domain.usecases.GetRecipiesUseCase
import com.example.fitnessprofront.features.recipies.domain.usecases.UpdateRecipeUseCase
import com.example.fitnessprofront.features.recipies.presentation.viewmodels.RecipiesViewModelFactory

class RecipeModule(
    private val appContainer: AppContainer
) {
    fun provideGetRecipiesUseCase(): GetRecipiesUseCase {
        return GetRecipiesUseCase(appContainer.recipeRepository)
    }

    fun provideCreateRecipeUseCase(): CreateRecipeUseCase {
        return CreateRecipeUseCase(appContainer.recipeRepository)
    }

    fun provideUpdateRecipeUseCase(): UpdateRecipeUseCase {
        return UpdateRecipeUseCase(appContainer.recipeRepository)
    }

    fun provideDeleteRecipeUseCase(): DeleteRecipeUseCase {
        return DeleteRecipeUseCase(appContainer.recipeRepository)
    }

    fun provideRecipiesViewModelFactory(): RecipiesViewModelFactory {
        return RecipiesViewModelFactory(
            provideGetRecipiesUseCase(),
            provideCreateRecipeUseCase(),
            provideUpdateRecipeUseCase(),
            provideDeleteRecipeUseCase()
        )
    }
}