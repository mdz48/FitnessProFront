package com.example.fitnessprofront.features.recipies.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fitnessprofront.features.recipies.presentation.screens.RecipesScreen
import com.example.fitnessprofront.features.recipies.presentation.viewmodels.RecipiesViewModelFactory

@Composable
fun HomeScreen(
    recipiesViewModelFactory: RecipiesViewModelFactory
) {
    RecipesScreen(
        factory = recipiesViewModelFactory,
        modifier = Modifier.fillMaxSize()
    )
}