package com.example.fitnessprofront.features.recipies.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessprofront.features.recipies.presentation.viewmodels.RecipiesViewModel
import com.example.fitnessprofront.features.recipies.presentation.viewmodels.RecipiesViewModelFactory

@Composable
fun RecipesScreen(
    factory: RecipiesViewModelFactory,
    modifier: Modifier = Modifier
) {
    val viewModel: RecipiesViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRecipies()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage ?: "",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn {
                items(uiState.recipies) { recipe ->
                    Text(text = recipe.name)
                }
            }
        }
    }
}