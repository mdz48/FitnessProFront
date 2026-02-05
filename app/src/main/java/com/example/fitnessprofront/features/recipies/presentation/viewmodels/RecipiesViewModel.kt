package com.example.fitnessprofront.features.recipies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessprofront.features.recipies.domain.usecases.CreateRecipeUseCase
import com.example.fitnessprofront.features.recipies.domain.usecases.GetRecipiesUseCase
import com.example.fitnessprofront.features.recipies.presentation.screens.RecipiesUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipiesViewModel(
    private val getRecipiesUseCase: GetRecipiesUseCase,
    private val createRecipeUseCase: CreateRecipeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipiesUIState())
    val uiState: StateFlow<RecipiesUIState> = _uiState.asStateFlow()

    fun getRecipies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val recipies = getRecipiesUseCase()
                _uiState.update { it.copy(isLoading = false, recipies = recipies) }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Error al obtener las recetas"
                    )
                }
            }
        }
    }

    fun createRecipe(
        name: String,
        description: String,
        ingredients: String,
        instructions: String,
        userId: Int? = null,
        scheduledDatetime: String? = null
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val newRecipe = createRecipeUseCase(
                    name = name,
                    description = description,
                    ingredients = ingredients,
                    instructions = instructions,
                    userId = userId,
                    scheduledDatetime = scheduledDatetime
                )

                // Actualizar la lista de recetas añadiendo la nueva receta
                val updatedRecipes = _uiState.value.recipies + newRecipe
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        recipies = updatedRecipes,
                        recipeCreated = true
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Error al crear la receta"
                    )
                }
            }
        }
    }

    fun resetRecipeCreated() {
        _uiState.update { it.copy(recipeCreated = false) }
    }
}